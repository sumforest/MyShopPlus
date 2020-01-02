package com.sen.myshop.plus.business.controller;
import java.util.Date;

import com.google.common.collect.Maps;
import com.sen.myshop.plus.business.BusinessException;
import com.sen.myshop.plus.business.BusinessStatus;
import com.sen.myshop.plus.business.dto.LoginDto;
import com.sen.myshop.plus.business.dto.LoginInfo;
import com.sen.myshop.plus.business.profile.feign.ProfileFeign;
import com.sen.myshop.plus.cloud.api.MessageService;
import com.sen.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.sen.myshop.plus.cloud.feign.MessageFeign;
import com.sen.myshop.plus.commons.dto.IpInfo;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import com.sen.myshop.plus.commons.utils.MapperUtils;
import com.sen.myshop.plus.commons.utils.OkHttpClientUtil;
import com.sen.myshop.plus.commons.utils.UserAgentUtils;
import com.sen.myshop.plus.ums.admin.provider.api.UmsAdminService;
import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import eu.bitwalker.useragentutils.UserAgent;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: Sen
 * @Date: 2019/12/29 02:10
 * @Description:
 */
@RestController
public class LoginController {

    @Value("${oauth2.client_id}")
    private String clientId;

    @Value("${oauth2.client_secret}")
    private String clientSecret;

    @Value("${oauth2.grant_type}")
    private String grantType;

    @Resource(name = "createUserDetailsService")
    private UserDetailsService userDetailsService;

    @Resource(name = "createBCryptPasswordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    @Resource
    private MessageFeign messageFeign;

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Reference(version = "1.0.0")
    private MessageService messageService;
    /**
     * 对接前端的登录请求
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        //从Security中的获取验证是否通过
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        //提前验证通过
        if (userDetails == null || !passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
        }
        String username = loginDto.getUsername();
        OkHttpClientUtil util = OkHttpClientUtil.getInstance();
        Map<String, String> params = Maps.newHashMap();
        params.put("username", username);
        params.put("password", loginDto.getPassword());
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("grant_type", grantType);
        String URL = "http://localhost:9001/oauth/token";
        Response response = util.postData(URL, params);
        Map<String, Object> result = null;
        try {
            String string = Objects.requireNonNull(response.body()).string();
            result = MapperUtils.json2map(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert result != null;
        String assess_token = (String) result.get("access_token");
        if (assess_token == null || assess_token.length() == 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "登录失败，获取token失败", null);
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("token", assess_token);

        //写入登录日志
        writeLoginLog(username, request);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "登录成功", map);
    }

    /**
     * 封装写入登录日志
     * @param username 用户名
     * @param request 获取用户登录信息
     */
    private void writeLoginLog(String username, HttpServletRequest request) {
        UmsAdmin admin = umsAdminService.getAdmin(username);
        //获取用户的登录信息
        String ipAddr = UserAgentUtils.getIpAddr(request);
        IpInfo ipInfo = UserAgentUtils.getIpInfo(ipAddr);
        UserAgent userAgent = UserAgentUtils.getUserAgent(request);

        UmsAdminLoginLogDTO loginLogDTO = new UmsAdminLoginLogDTO();
        loginLogDTO.setAdminId(admin.getId());
        loginLogDTO.setCreateTime(new Date());
        loginLogDTO.setIp(ipAddr);
        loginLogDTO.setAddress(ipInfo.getCity());
        loginLogDTO.setUserAgent(userAgent.getOperatingSystem().getName());
        System.out.println("---------------------发送消息完成--------------------------");
        //发送写日志消息
        //由于登陆成功后token还没有放进request中，所以此时feign请求Http的日志服务将没有权限
        // String message = messageFeign.sendAdminLoginLogRest(loginLogDTO);

        //发送日志通过Dubbo服务
        boolean flag = messageService.sendAdminLoginLog(loginLogDTO);
        System.out.println(flag);
    }

    /**
     * 获取登录用户的信息
     * @return
     */
    @GetMapping("/user/info")
    public ResponseResult<LoginInfo> userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String json = profileFeign.profileInfo(authentication.getName());
        LoginInfo loginInfo = new LoginInfo();
        try {
            UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(json, "data", UmsAdmin.class);
            //用户名不存在证明已经熔断
            if (umsAdmin.getUsername() == null) {
                return MapperUtils.json2pojo(json, ResponseResult.class);
            }
            loginInfo.setName(umsAdmin.getUsername());
            loginInfo.setAvatar(umsAdmin.getIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取信息成功", loginInfo);
    }

    @PostMapping("/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request){
        String access_token = request.getParameter("access_token");
        //读取token
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        //删除token
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"已注销");
    }
}

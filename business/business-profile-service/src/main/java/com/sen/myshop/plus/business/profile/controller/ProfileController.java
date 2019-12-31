package com.sen.myshop.plus.business.profile.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sen.myshop.plus.business.profile.controller.fackback.ProfileControllerFallback;
import com.sen.myshop.plus.business.profile.dto.params.IconParam;
import com.sen.myshop.plus.business.profile.dto.params.ProfileParam;
import com.sen.myshop.plus.business.profile.feign.fallback.ProfileFeignFallBack;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import com.sen.myshop.plus.ums.admin.provider.api.UmsAdminService;
import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 00:56
 * @Description: 个人信息管理
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    /**
     * 查询用户信息
     * @param username 查询条件
     * @return
     */
    @GetMapping(value = "/info/{username}")
    @SentinelResource(value = "profileInfo",fallback = "profileInfoFallback",fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<UmsAdmin> profileInfo(@PathVariable String username) {
        UmsAdmin admin = umsAdminService.getAdmin(username);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "查询用户信息", admin);
    }

    /**
     * 更新用户信息
     * @param profileParam
     * @return
     */
    @PostMapping(value = "/update")
    public ResponseResult<Void> updateProfile(@RequestBody ProfileParam profileParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(profileParam, umsAdmin);
        int result = umsAdminService.Update(umsAdmin);

        //更新成功
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新用户信息成功");
        }
        //更新失败
        else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "更新用户信息失败");
        }
    }

    /**
     * 修改头像
     * @param iconParam 修改头像参数dto
     * @return 返回信息
     */
    @PostMapping(value = "/modify/icon")
    public ResponseResult<Void> modifyIcon(@RequestBody IconParam iconParam) {
        int result = umsAdminService.modifyIcon(iconParam.getUsername(), iconParam.getPath());
        //更新成功
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改头像成功");
        }
        //更新失败
        else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "修改头像失败");
        }
    }
}

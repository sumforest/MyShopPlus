package com.sen.myshop.plus.business.controller;

import com.sen.myshop.plus.commons.dto.ResponseResult;
import com.sen.myshop.plus.ums.admin.provider.api.UmsAdminService;
import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 22:31
 * @Description: 用户注册
 */
@RestController
@RequestMapping(value = "/reg")
public class RegisterController {

    @Reference
    private UmsAdminService umsAdminService;

    /**
     * 注册管理员
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link ResponseResult}
     */
    @PostMapping("")
    public ResponseResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin){
        String message = validate(umsAdmin);
        //验证通过
        if (message == null) {
            int result = umsAdminService.insert(umsAdmin);
            if (result > 0) {
                UmsAdmin adminFormDb = umsAdminService.getAdmin(umsAdmin.getUsername());
                return new ResponseResult<>(HttpStatus.OK.value(),"用户注册成功",adminFormDb);
            }
        }
        return new ResponseResult<>(HttpStatus.NOT_ACCEPTABLE.value(),message == null ? "发生未知错误":message);
    }

    /**
     * 注册校验
     * @param umsAdmin {@link UmsAdmin}
     * @return 校验通过return null
     */
    private String validate(UmsAdmin umsAdmin) {
        if (umsAdminService.getAdmin(umsAdmin.getUsername()) != null) {
            return "用户名已存在，注册失败";
        }
        return null;
    }
}

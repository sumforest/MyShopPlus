package com.sen.myshop.plus.business.controller;

import com.sen.myshop.plus.ums.admin.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 20:11
 * @Description:
 */
@RestController
@RequestMapping("echo")
public class EchoController {

    @Reference(version = "1.0.0")
    private EchoService echoService;

    @GetMapping(value = "{value}")
    public String echo(@PathVariable String value) {
        return echoService.EchoTest(value);
    }
}

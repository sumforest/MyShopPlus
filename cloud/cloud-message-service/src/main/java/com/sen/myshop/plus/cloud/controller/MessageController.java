package com.sen.myshop.plus.cloud.controller;

import com.sen.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.sen.myshop.plus.cloud.producer.MessageProducer;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 15:14
 * @Description:
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Resource
    private MessageProducer producer;

    @PostMapping(value = "/admin/login/log")
    public ResponseResult<Void> sendMessage(@RequestBody UmsAdminLoginLogDTO dto) {
        boolean flag = producer.sendAdminLoginLogRest(dto);
        //发送消息成功
        if (flag){
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "发送消息成功");
        }
        //发送消息失败
        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "发送消息失败");
    }
}

package com.sen.myshop.plus.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 15:05
 * @Description:
 */
public interface MessageResource {

    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();
}

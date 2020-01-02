package com.sen.myshop.plus.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 18:47
 * @Description:
 */
public interface AdminLoginLogSink {

    @Input("admin-login-log-topic")
    SubscribableChannel adminLoginLogSink();
}

package com.sen.myshop.plus.message;

import com.sen.myshop.plus.message.sink.AdminLoginLogSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 18:40
 * @Description:
 */
@SpringBootApplication
@EnableBinding({AdminLoginLogSink.class})
public class ConsumerAdminLoginLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerAdminLoginLogApplication.class, args);
    }
}

package com.sen.myshop.plus.cloud;

import com.sen.myshop.plus.cloud.message.MessageResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 15:02
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MessageResource.class})
public class ProducerMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerMessageApplication.class, args);
    }
}

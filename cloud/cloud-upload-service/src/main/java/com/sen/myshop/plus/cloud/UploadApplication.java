package com.sen.myshop.plus.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 19:14
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}

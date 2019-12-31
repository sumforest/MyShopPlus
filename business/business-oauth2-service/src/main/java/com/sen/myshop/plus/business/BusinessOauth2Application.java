package com.sen.myshop.plus.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 23:33
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(BusinessOauth2Application.class, args);
    }
}

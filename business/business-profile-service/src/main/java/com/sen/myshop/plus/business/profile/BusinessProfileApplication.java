package com.sen.myshop.plus.business.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 00:55
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileApplication.class, args);
    }
}

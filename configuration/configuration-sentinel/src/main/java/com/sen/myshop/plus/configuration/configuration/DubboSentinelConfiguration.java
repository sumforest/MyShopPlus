package com.sen.myshop.plus.configuration.configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Sen
 * @Date: 2020/1/1 01:57
 * @Description: 此配置只用与Dubbo服务提供者熔断使用Spring Cloud服务不需此配置
 */
@Configuration
public class DubboSentinelConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}

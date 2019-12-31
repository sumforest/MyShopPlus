package com.sen.myshop.plus.configuration.configuration;

import com.sen.myshop.plus.configuration.interceptor.FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 02:44
 * @Description:
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }
}

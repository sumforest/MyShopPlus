package com.sen.myshop.plus.ums.admin.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 20:31
 * @Description:
 */
@Configuration
public class BCryptPasswordEncoderConfig {

    /**
     * 注入加密器
     * @return 返回单例加密器
     */
    @Bean
    public BCryptPasswordEncoder createBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

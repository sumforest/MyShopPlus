package com.sen.myshop.plus.ums.login.log.provider;

import com.sen.myshop.plus.configuration.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Auther: Sen
 * @Date: 2020/1/1 23:41
 * @Description:
 */
@SpringBootApplication(scanBasePackageClasses = {UmsAdminLoginLogApplication.class, DubboSentinelConfiguration.class})
@MapperScan("com.sen.myshop.plus.ums.login.log.provider.mapper")
public class UmsAdminLoginLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsAdminLoginLogApplication.class, args);
    }
}

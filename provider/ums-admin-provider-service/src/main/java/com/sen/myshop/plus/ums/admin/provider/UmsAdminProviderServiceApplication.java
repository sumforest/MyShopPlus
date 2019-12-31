package com.sen.myshop.plus.ums.admin.provider;

import com.sen.myshop.plus.configuration.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 17:04
 * @Description:
 */
@SpringBootApplication(scanBasePackageClasses = {UmsAdminProviderServiceApplication.class, DubboSentinelConfiguration.class})
@MapperScan("com.sen.myshop.plus.ums.admin.provider.mapper")
public class UmsAdminProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmsAdminProviderServiceApplication.class, args);
    }
}

package com.sen.myshop.plus.ums.admin.provider.service.fallback;

import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: Sen
 * @Date: 2020/1/1 02:07
 * @Description: Dubbo服务提供者熔断
 */
public class UmsAdminServiceFallback {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceFallback.class);

    /**
     * 登录熔断
     * @param username 用户名
     * @param throwable 捕获被熔断方法的异常
     * @return {@link UmsAdmin} 熔断结果
     */
    public static UmsAdmin getByUsernameFallback(String username,Throwable throwable) {
        logger.warn("触发熔断,捕获的异常: " + throwable.getClass().getName());
        return null;
    }
}

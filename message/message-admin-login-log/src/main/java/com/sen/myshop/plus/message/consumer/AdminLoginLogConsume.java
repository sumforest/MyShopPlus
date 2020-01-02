package com.sen.myshop.plus.message.consumer;

import com.sen.myshop.plus.commons.utils.MapperUtils;
import com.sen.myshop.plus.ums.login.log.provider.api.UmsAdminLoginLogService;
import com.sen.myshop.plus.ums.login.log.provider.domain.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 18:49
 * @Description:
 */
@Service
public class AdminLoginLogConsume {

    @Reference(version = "1.0.0")
    private UmsAdminLoginLogService logService;

    @StreamListener("admin-login-log-topic")
    public void consumeMessage(String dto) {
        try {
            UmsAdminLoginLog umsAdminLoginLog = MapperUtils.json2pojo(dto, UmsAdminLoginLog.class);
            logService.insert(umsAdminLoginLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

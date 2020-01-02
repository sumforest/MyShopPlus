package com.sen.myshop.plus.cloud.producer;

import com.sen.myshop.plus.cloud.api.MessageService;
import com.sen.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.sen.myshop.plus.cloud.message.MessageResource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 15:10
 * @Description:
 */
@Component
@Service(version = "1.0.0")
public class MessageProducer implements MessageService {

    @Resource
    private MessageResource resource;

    /**
     * 提供Http日志服务
     * @param dto
     * @return
     */
    public boolean sendAdminLoginLogRest(UmsAdminLoginLogDTO dto) {
        return resource.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }

    /**
     * 提供Dubbo日志服务
     * @param dto
     * @return
     */
    @Override
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
        return resource.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }
}

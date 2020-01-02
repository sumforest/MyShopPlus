package com.sen.myshop.plus.cloud.feign;

import com.sen.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.sen.myshop.plus.cloud.feign.fallback.MessageFeignFallback;
import com.sen.myshop.plus.configuration.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 14:47
 * @Description:
 */
@FeignClient(value = "cloud-message-feign", path = "message", configuration = FeignConfiguration.class, fallback = MessageFeignFallback.class)

public interface MessageFeign {

    @PostMapping(value = "admin/login/log")
    String sendAdminLoginLogRest(@RequestBody UmsAdminLoginLogDTO dto);
}

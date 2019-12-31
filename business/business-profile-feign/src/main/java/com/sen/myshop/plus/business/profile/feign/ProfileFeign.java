package com.sen.myshop.plus.business.profile.feign;

import com.sen.myshop.plus.business.profile.dto.params.IconParam;
import com.sen.myshop.plus.business.profile.dto.params.ProfileParam;
import com.sen.myshop.plus.business.profile.feign.fallback.ProfileFeignFallBack;
import com.sen.myshop.plus.configuration.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 01:18
 * @Description:
 */

@FeignClient(name = "business-profile-service",path = "profile",configuration = FeignConfiguration.class,fallback = ProfileFeignFallBack.class)
public interface ProfileFeign {

    @GetMapping("/info/{username}")
    String profileInfo(@PathVariable String username);

    @PostMapping("/update")
    String updateProfile(@RequestBody ProfileParam profileParam);

    @GetMapping("/modify/icon")
    String modifyIcon(@RequestBody IconParam iconParam);
}

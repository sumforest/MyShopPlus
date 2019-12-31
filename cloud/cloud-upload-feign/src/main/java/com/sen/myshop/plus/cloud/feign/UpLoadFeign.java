package com.sen.myshop.plus.cloud.feign;

import com.sen.myshop.plus.configuration.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
/**
 * @Auther: Sen
 * @Date: 2019/12/31 19:04
 * @Description:
 */
@FeignClient(value = "cloud-upload-service",path = "upload",configuration = FeignConfiguration.class)
public interface UpLoadFeign {

    @PostMapping(value = "")
    String upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile);
}

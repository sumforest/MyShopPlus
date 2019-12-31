package com.sen.myshop.plus.business.profile.controller.fackback;

import com.sen.myshop.plus.business.profile.dto.UmsAdminDTO;
import com.sen.myshop.plus.business.profile.feign.fallback.ProfileFeignFallBack;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Auther: Sen
 * @Date: 2020/1/1 02:39
 * @Description:
 */
public class ProfileControllerFallback {

    private final static  Logger logger = LoggerFactory.getLogger(ProfileControllerFallback.class);

    public static ResponseResult<UmsAdminDTO> profileInfoFallback(String username,Throwable ex){
        logger.warn("触熔断，异常：" + ex.getClass().getName());
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, ProfileFeignFallBack.BREAKING_MESSAGE);
    }
}

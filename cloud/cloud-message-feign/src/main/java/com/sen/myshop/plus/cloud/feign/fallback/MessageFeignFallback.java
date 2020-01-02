package com.sen.myshop.plus.cloud.feign.fallback;

import com.sen.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.sen.myshop.plus.cloud.feign.MessageFeign;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import com.sen.myshop.plus.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 14:49
 * @Description:
 */
@Component
public class MessageFeignFallback implements MessageFeign {

    private static String BREAKING_MESSAGE ="您的网络有问题，请检查";


    @Override
    public String sendAdminLoginLogRest(UmsAdminLoginLogDTO dto) {
        try {
            return MapperUtils.obj2json(new ResponseResult<>(ResponseResult.CodeStatus.OK,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

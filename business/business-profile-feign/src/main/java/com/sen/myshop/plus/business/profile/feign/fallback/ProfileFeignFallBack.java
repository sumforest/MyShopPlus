package com.sen.myshop.plus.business.profile.feign.fallback;

import com.sen.myshop.plus.business.profile.dto.UmsAdminDTO;
import com.sen.myshop.plus.business.profile.dto.params.IconParam;
import com.sen.myshop.plus.business.profile.dto.params.ProfileParam;
import com.sen.myshop.plus.business.profile.feign.ProfileFeign;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import com.sen.myshop.plus.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 23:55
 * @Description:
 */
@Component
public class ProfileFeignFallBack implements ProfileFeign {

    public static String BREAKING_MESSAGE ="您的网络有问题，请检查";

    @Override
    public String profileInfo(String username) {
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        umsAdminDTO.setEmail("sen.forest@qq.com");
        try {
            return MapperUtils.obj2json(new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE, umsAdminDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateProfile(ProfileParam profileParam) {
        return null;
    }

    @Override
    public String modifyIcon(IconParam iconParam) {
        return null;
    }
}

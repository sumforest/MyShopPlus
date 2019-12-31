package com.sen.myshop.plus.ums.admin.provider.service.iml;


import com.sen.myshop.plus.ums.admin.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 17:06
 * @Description:
 */
@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {


    @Override
    public String EchoTest(String string) {
        return "hello nacos" + string;
    }

}

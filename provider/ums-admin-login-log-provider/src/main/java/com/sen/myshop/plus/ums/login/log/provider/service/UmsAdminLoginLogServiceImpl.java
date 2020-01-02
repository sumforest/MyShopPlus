package com.sen.myshop.plus.ums.login.log.provider.service;

import javax.annotation.Resource;
import com.sen.myshop.plus.ums.login.log.provider.domain.UmsAdminLoginLog;
import com.sen.myshop.plus.ums.login.log.provider.mapper.UmsAdminLoginLogMapper;
import com.sen.myshop.plus.ums.login.log.provider.api.UmsAdminLoginLogService;
import org.apache.dubbo.config.annotation.Service;
/**
 * @Auther: Sen
 * @Date: 2020/1/1 23:50
 * @Description: 
 */
@Service(version = "1.0.0")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}

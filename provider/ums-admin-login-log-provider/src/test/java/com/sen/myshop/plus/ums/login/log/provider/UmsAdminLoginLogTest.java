package com.sen.myshop.plus.ums.login.log.provider;
import java.util.Date;

import com.sen.myshop.plus.ums.login.log.provider.api.UmsAdminLoginLogService;
import com.sen.myshop.plus.ums.login.log.provider.domain.UmsAdminLoginLog;
import com.sen.myshop.plus.ums.login.log.provider.mapper.UmsAdminLoginLogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * @Auther: Sen
 * @Date: 2020/1/1 23:56
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UmsAdminLoginLogTest {

    @Resource
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @Test
    public void insertTest(){
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();
        umsAdminLoginLog.setAdminId(0L);
        umsAdminLoginLog.setCreateTime(new Date());
        umsAdminLoginLog.setIp("0.0.0.0");
        umsAdminLoginLog.setAddress("0.0.0.0");
        umsAdminLoginLog.setUserAgent("0.0.0.0");

        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }
}

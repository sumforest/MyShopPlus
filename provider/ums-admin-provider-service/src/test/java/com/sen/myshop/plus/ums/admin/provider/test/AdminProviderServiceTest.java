package com.sen.myshop.plus.ums.admin.provider.test;

import com.sen.myshop.plus.ums.admin.provider.api.UmsAdminService;
import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import com.sen.myshop.plus.ums.admin.provider.mapper.UmsAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 18:43
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminProviderServiceTest {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testDb(){
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        umsAdmins.forEach(System.out::println);
    }

    @Test
    public void RegisterAdminTest(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("sen");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        umsAdmin.setIcon("");
        umsAdmin.setEmail("123456@qq.com");
        umsAdmin.setNickName("超级管理员");
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        umsAdmin.setStatus(0);
        umsAdminService.insert(umsAdmin);
    }
}

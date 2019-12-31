package com.sen.myshop.plus.ums.admin.provider.service.iml;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sen.myshop.plus.ums.admin.provider.api.UmsAdminService;
import com.sen.myshop.plus.ums.admin.provider.mapper.UmsAdminMapper;
import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import com.sen.myshop.plus.ums.admin.provider.service.fallback.UmsAdminServiceFallback;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 20:26
 * @Description:
 */
@Service(version = "1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService {

    /**
     * 线程安全注解,@Autowired线程不安全注解
     */
    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        initUmsAdmin(umsAdmin);
        return umsAdminMapper.insertSelective(umsAdmin);
    }

    /**
     * 插入新管理员时初始化
     *
     * @param umsAdmin {@link UmsAdmin}
     */
    private void initUmsAdmin(UmsAdmin umsAdmin) {
        //初始化时间
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());

        //加密明文密码
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));

        //初始化状态
        if (umsAdmin.getStatus() == null) {
            umsAdmin.setStatus(0);
        }
    }

    @Override
    @SentinelResource(value = "getByUsername",fallback = "getByUsernameFallback",fallbackClass = UmsAdminServiceFallback.class)
    public UmsAdmin getAdmin(String username) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username", username);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public UmsAdmin getAdmin(UmsAdmin umsAdmin) {
        return umsAdminMapper.selectOne(umsAdmin);
    }

    @Override
    public int Update(UmsAdmin umsAdmin) {
        UmsAdmin oldUmsAdmin = getAdmin(umsAdmin.getUsername());
        oldUmsAdmin.setEmail(umsAdmin.getEmail());
        oldUmsAdmin.setNickName(umsAdmin.getNickName());
        oldUmsAdmin.setNote(umsAdmin.getNote());
        oldUmsAdmin.setStatus(umsAdmin.getStatus());
        return umsAdminMapper.updateByPrimaryKeySelective(oldUmsAdmin);
    }

    @Override
    public int modifyIcon(String username, String path) {
        UmsAdmin admin = getAdmin(username);
        admin.setIcon(path);
        return umsAdminMapper.updateByPrimaryKey(admin);
    }
}

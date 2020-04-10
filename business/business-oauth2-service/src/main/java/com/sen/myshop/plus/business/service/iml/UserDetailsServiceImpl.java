package com.sen.myshop.plus.business.service.iml;

import com.sen.myshop.plus.ums.admin.provider.api.UmsAdminService;
import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 23:40
 * @Description: 实现 {@link UserDetailsService}
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UmsAdmin admin = umsAdminService.getAdmin(s);
        if (admin != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //所有用户默认有一个User角色
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("User");
            grantedAuthorities.add(simpleGrantedAuthority);
            return new User(admin.getUsername(), admin.getPassword(), grantedAuthorities);
        }
       return null;
    }
}

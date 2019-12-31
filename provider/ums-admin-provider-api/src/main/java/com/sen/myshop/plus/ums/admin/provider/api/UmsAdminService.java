package com.sen.myshop.plus.ums.admin.provider.api;

import com.sen.myshop.plus.ums.admin.provider.domain.UmsAdmin;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 20:25
 * @Description:
 */
public interface UmsAdminService {

    /**
     * 新增用户
     * @param umsAdmin {@link UmsAdmin}
     * @return 返回影响的行数
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 查找用户
     * @param username 用户名
     * @return {@link UmsAdmin}
     */
    UmsAdmin getAdmin(String username);

    /**
     * 查找用户
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link UmsAdmin}
     */
    UmsAdmin getAdmin(UmsAdmin umsAdmin);

    /**
     * 更新用户信息
     * @param umsAdmin {@link UmsAdmin}
     * @return 首影响的行数
     */
    int Update(UmsAdmin umsAdmin);

    /**
     * 修改头像
     * @param username 用户名
     * @param path 头像地址
     * @return {@code int} 大于0修改成功
     */
    int modifyIcon(String username, String path);
}

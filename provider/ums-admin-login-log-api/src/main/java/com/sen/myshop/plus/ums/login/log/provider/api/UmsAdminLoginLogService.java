package com.sen.myshop.plus.ums.login.log.provider.api;

import com.sen.myshop.plus.ums.login.log.provider.domain.UmsAdminLoginLog;

/**
 * @Auther: Sen
 * @Date: 2020/1/1 23:50
 * @Description: 
 */
public interface UmsAdminLoginLogService{

    /**
     * 插入日志
     * @param umsAdminLoginLog {@link UmsAdminLoginLog}
     * @return 大于零插入成功
     */
    int insert(UmsAdminLoginLog umsAdminLoginLog);
}

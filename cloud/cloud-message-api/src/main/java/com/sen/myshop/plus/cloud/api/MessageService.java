package com.sen.myshop.plus.cloud.api;


import com.sen.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 22:09
 * @Description:
 */
public interface MessageService {
    /**
     * 发送消息
     * @param dto
     * @return
     */
    boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto);
}

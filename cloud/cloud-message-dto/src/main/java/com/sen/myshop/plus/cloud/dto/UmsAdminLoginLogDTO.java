package com.sen.myshop.plus.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Sen
 * @Date: 2020/1/2 14:44
 * @Description:
 */
@Data
public class UmsAdminLoginLogDTO implements Serializable {

    private static final long serialVersionUID = 5635398676969149792L;

    private Long adminId;

    private Date createTime;

    private String ip;

    private String address;

    /**
     * 浏览器登录类型
     */
    private String userAgent;
}

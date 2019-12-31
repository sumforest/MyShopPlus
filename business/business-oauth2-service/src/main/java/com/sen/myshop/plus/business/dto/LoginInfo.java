package com.sen.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Sen
 * @Date: 2019/12/30 22:48
 * @Description: 返回登录信息对象
 */
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 2692525069457870617L;

    private String name;

    private String avatar;
}

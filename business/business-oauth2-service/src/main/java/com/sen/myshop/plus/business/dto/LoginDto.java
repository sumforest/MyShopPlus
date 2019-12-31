package com.sen.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Sen
 * @Date: 2019/12/29 02:40
 * @Description:
 */
@Data
public class LoginDto implements Serializable {

    private String username;

    private String password;
}

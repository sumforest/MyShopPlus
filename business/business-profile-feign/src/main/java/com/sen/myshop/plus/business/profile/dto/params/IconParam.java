package com.sen.myshop.plus.business.profile.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 21:56
 * @Description:
 */
@Data
public class IconParam implements Serializable {

    private static final long serialVersionUID = 1381636360887598208L;

    private String username;

    private String path;
}

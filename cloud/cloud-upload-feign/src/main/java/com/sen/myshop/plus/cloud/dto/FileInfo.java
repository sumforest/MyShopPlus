package com.sen.myshop.plus.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 19:05
 * @Description: 用于接收上传文件返回参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    /**
     * 上传文件返回的路径
     */
    private String path;
}

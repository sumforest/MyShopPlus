package com.sen.myshop.plus.cloud.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.sen.myshop.plus.cloud.dto.FileInfo;
import com.sen.myshop.plus.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 19:23
 * @Description:
 */
@RestController
@RequestMapping(value = "upload")
public class UploadFileController {

    private static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAIUEM4x1YOqT0O";
    private static final String ACCESS_KEY_SECRET = "XVRECYNWqS7uzssIXeNrcgKIamBjTh";
    private static final String BUCKET_NAME = "javasite";

    @PostMapping(value = "")
    public ResponseResult<FileInfo> upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        String newFilename = UUID.randomUUID().toString() + "." + suffix;

        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            client.putObject(BUCKET_NAME, newFilename, new ByteArrayInputStream(multipartFile.getBytes()));

            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "文件上传成功", new FileInfo("http://" + BUCKET_NAME + "." + ENDPOINT + "/" + newFilename));
        } catch (IOException e) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "文件按上传失败");
        } finally {
            //关闭资源
            client.shutdown();
        }
    }

}

package com.sen.myshop.plus.business;

import com.google.common.collect.Maps;
import com.sen.myshop.plus.commons.utils.MapperUtils;
import com.sen.myshop.plus.commons.utils.OkHttpClientUtil;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: Sen
 * @Date: 2019/12/30 19:40
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OkHttp3Test {

    /**
     * 测试get方法
     */
    @Test
    public void getMethodTest(){
        String url = "https://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postMethodTest(){
        String url = "http://localhost:9001/oauth/token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .add("client_id", "client")
                .add("client_secret", "secret")
                .add("grant_type", "password")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void utilPostMethodTest(){
        OkHttpClientUtil util = OkHttpClientUtil.getInstance();
        String url = "http://localhost:9001/oauth/token";
        Map<String,String> params = Maps.newHashMap();
        params.put("username", "admin");
        params.put("password", "123456");
        params.put("client_id", "client");
        params.put("client_secret", "secret");
        params.put("grant_type", "password");
        Response response = util.postData(url, params);
        try {
            String string = response.body().string();
            Map<String, Object> result = MapperUtils.json2map(string);
            String assess_token = (String) result.get("access_token");
            System.out.println(assess_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

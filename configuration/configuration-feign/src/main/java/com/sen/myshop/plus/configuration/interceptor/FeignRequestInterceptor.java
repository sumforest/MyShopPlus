package com.sen.myshop.plus.configuration.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * @Auther: Sen
 * @Date: 2019/12/31 02:19
 * @Description: 实现RequestInterceptor拦截器，在feign调用用Spring Cloud服务的时候带上access_token
 * 实现单点登录
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            //封装请求头
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                requestTemplate.header(key, value);
            }
        }

        //获取请求体
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder stringBuilder = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                String parameter = request.getParameter(key);

                //如果参数是access_token放入Feign请求头中
                if ("access_token".equals(key)) {
                    requestTemplate.header("authorization", "Bearer" + parameter);
                }
                //其他参数封装进请求体
                else {
                    stringBuilder.append(key).append("=").append(parameter).append("&");
                }
            }
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            requestTemplate.body(Request.Body.bodyTemplate(stringBuilder.toString(), Charset.defaultCharset()));
        }
    }
}

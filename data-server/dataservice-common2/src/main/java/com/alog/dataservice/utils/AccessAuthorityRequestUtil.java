package com.alog.dataservice.utils;

import net.sf.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class AccessAuthorityRequestUtil {

    public static String authtoken(String token, Map<String,String> loginUser,String verifyTokenUrl,String configServerUrl,String userauth,String serviceId){
        RestTemplate restTemplate = new RestTemplate();
        // -------------------------------> 解决(响应数据可能)中文乱码 的问题
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.remove(1); // 移除原来的转换器
        // 设置字符编码为utf-8
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter); // 添加新的转换器(注:convert顺序错误会导致失败)
        restTemplate.setMessageConverters(converterList);
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置contentType
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 给请求header中添加一些数据
        httpHeaders.add("token", token);
        httpHeaders.add("serviceId", serviceId);
        httpHeaders.add("userauth", userauth);
        JSONObject json = JSONObject.fromObject(loginUser);
        HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(), httpHeaders);
        String url = verifyTokenUrl;
        URI uri = URI.create(url);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);

        // -------------------------------> 响应信息
        //响应码,如:401、302、404、500、200等
        System.err.println(response.getStatusCodeValue());
        if(response.hasBody()) {
            System.err.println(response.getBody());
        }

        if(response.getBody().equals("0")){
            return "0";
        }else{
            return "1";
        }
    }
}

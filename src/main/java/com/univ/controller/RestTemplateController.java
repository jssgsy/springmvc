package com.univ.controller;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * @author univ
 * @date 2019/5/5 11:05 AM
 * @description
 */
@RestController
@RequestMapping("/rest")
public class RestTemplateController {
    
    private RestTemplate restTemplate;
    
    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();


    }

    /**
     * 测试最基本的get请求,不带任何参数
     * @return
     */
    @RequestMapping("/get")
    public String basicGet() {
        String str = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println(str);

        // 带参数
        String str1 = restTemplate.getForObject("http://127.0.0.1:8080/home/requestparam?id={?}", String.class, 888);
        System.out.println(str1);
        return "home";
    }

    /**
     * 添加header
     * @return
     */
    @RequestMapping("/header")
    public String advancedGet() {
        HttpHeaders h = new HttpHeaders();
        h.add("myheader", "hello, header");
        // h.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(h);
        JSONObject jsonObject = restTemplate.exchange("http://127.0.0.1:8080/date/default", HttpMethod.GET, httpEntity, JSONObject.class).getBody();

        System.out.println(jsonObject);
        return jsonObject.toJSONString();
    }
}

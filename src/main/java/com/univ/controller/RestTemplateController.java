package com.univ.controller;

import java.util.Arrays;
import javax.annotation.PostConstruct;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.univ.common.response.BaseResult;
import com.univ.domain.RemoteDomain;

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

    @RequestMapping("/remoteObj")
    public BaseResult.PlainResult<RemoteDomain> remoteObj() {
        RemoteDomain r1 = new RemoteDomain().setEmail("aaa@163.com").setId(100l).setRealname("zhangsan");
        BaseResult.PlainResult<RemoteDomain> result = new BaseResult.PlainResult<>();
        result.setData(r1);
        return result;
    }

    /**
     * 高级用法：将返回的数据映射成相应的对象(plainResult中的data是对象)，如此，则不用在代码中进行强转
     * 数据来源：上面的remoteObj方法
     * @return
     */
    @RequestMapping("/obj")
    public BaseResult.PlainResult<RemoteDomain> obj() {
        HttpHeaders h = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(h);

        // 利用ParameterizedTypeReference，将返回的结果映射成PlainResult<RemoteDomain>类型
        ParameterizedTypeReference<BaseResult.PlainResult<RemoteDomain>> typeRef = new ParameterizedTypeReference<BaseResult.PlainResult<RemoteDomain>>() {};
        BaseResult.PlainResult<RemoteDomain> result = restTemplate.exchange("http://127.0.0.1:8080/rest/remoteObj", HttpMethod.GET, httpEntity, typeRef).getBody();
        System.out.println(result);
        return result;
    }


    @RequestMapping("/remoteArr")
    public BaseResult.ListResult<RemoteDomain> remoteArr() {
        RemoteDomain r1 = new RemoteDomain().setEmail("aaa@163.com").setId(100l).setRealname("zhangsan");
        RemoteDomain r2 = new RemoteDomain().setEmail("bbb@163.com").setId(200l).setRealname("lisi");
        RemoteDomain r3 = new RemoteDomain().setEmail("ccc@163.com").setId(300l).setRealname("wangwu");

        BaseResult.ListResult<RemoteDomain> result = new BaseResult.ListResult<>();
        result.setData(Arrays.asList(r1, r2, r3));

        return result;
    }

    /**
     * 高级用法：将返回的数据映射成相应的数组(plainResult中的data是数组)，如此，则不用在代码中进行强转
     * 数据来源：上面的remoteArr方法
     * @return
     */
    @RequestMapping("/arr")
    public BaseResult.ListResult<RemoteDomain> arr() {
        HttpHeaders h = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(h);

        // 利用ParameterizedTypeReference，将返回的结果映射成ListResult<RemoteDomain>类型
        ParameterizedTypeReference<BaseResult.ListResult<RemoteDomain>> typeRef = new ParameterizedTypeReference<BaseResult.ListResult<RemoteDomain>>() {};
        BaseResult.ListResult<RemoteDomain> result = restTemplate.exchange("http://127.0.0.1:8080/rest/remoteArr", HttpMethod.GET, httpEntity, typeRef).getBody();
        System.out.println(result);
        return result;
    }
}

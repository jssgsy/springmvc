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
import com.univ.common.response.ListResult;
import com.univ.common.response.PlainResult;
import com.univ.controller.util.RestTemplateUtil;
import com.univ.domain.RemoteDest;
import com.univ.domain.RemoteSource;

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
        try {
            RestTemplateUtil.disableSSLValidation();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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
    public PlainResult<RemoteSource> remoteObj() {
        RemoteSource r1 = new RemoteSource().setEmail("aaa@163.com").setId(100l).setRealname("zhangsan");
        PlainResult<RemoteSource> result = new PlainResult<>();
        result.setData(r1);
        return result;
    }

    /**
     * 高级用法：将返回的数据映射成相应的对象(plainResult中的data是对象)，如此，则不用在代码中进行强转
     * 数据来源：上面的remoteObj方法
     * @return
     */
    @RequestMapping("/obj")
    public PlainResult<RemoteDest> obj() {
        HttpHeaders h = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(h);

        // 利用ParameterizedTypeReference，将返回的结果映射成PlainResult<RemoteSource>类型
        ParameterizedTypeReference<PlainResult<RemoteDest>> typeRef = new ParameterizedTypeReference<PlainResult<RemoteDest>>() {};
        PlainResult<RemoteDest> result = restTemplate.exchange("http://127.0.0.1:8080/rest/remoteObj", HttpMethod.GET, httpEntity, typeRef).getBody();
        System.out.println(result);
        return result;
    }


    @RequestMapping("/remoteArr")
    public ListResult<RemoteSource> remoteArr() {
        RemoteSource r1 = new RemoteSource().setEmail("aaa@163.com").setId(100l).setRealname("zhangsan");
        RemoteSource r2 = new RemoteSource().setEmail("bbb@163.com").setId(200l).setRealname("lisi");
        RemoteSource r3 = new RemoteSource().setEmail("ccc@163.com").setId(300l).setRealname("wangwu");

        ListResult<RemoteSource> result = new ListResult<>();
        result.setData(Arrays.asList(r1, r2, r3));

        return result;
    }

    /**
     * 高级用法：将返回的数据映射成相应的数组(plainResult中的data是数组)，如此，则不用在代码中进行强转
     * 数据来源：上面的remoteArr方法
     * @return
     */
    @RequestMapping("/arr")
    public ListResult<RemoteSource> arr() {
        HttpHeaders h = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(h);

        // 利用ParameterizedTypeReference，将返回的结果映射成ListResult<RemoteSource>类型
        ParameterizedTypeReference<ListResult<RemoteSource>> typeRef = new ParameterizedTypeReference<ListResult<RemoteSource>>() {};
        ListResult<RemoteSource> result = restTemplate.exchange("http://127.0.0.1:8080/rest/remoteArr", HttpMethod.GET, httpEntity, typeRef).getBody();
        System.out.println(result);
        return result;
    }
}

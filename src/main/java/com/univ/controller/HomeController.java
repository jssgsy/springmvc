package com.univ.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.univ.domain.Demo;
import com.univ.domain.ValidatedDemo;

/**
 * @author univ
 * @datetime 2018/11/19 8:05 PM
 * @description
 */
@Controller
@RequestMapping("/home")
@Validated
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        System.out.println("hello. home");
        return "home";
    }

    /**
     * 使用@ResponseBody返回json格式
     * 需要引入jackson-databind包，spring-webmvc没有默认依赖
     *
     * @return
     */
    @RequestMapping("/json")
    @ResponseBody
    public List second() {
        List list = new ArrayList();

        List l2 = new ArrayList();
        Demo d1 = new Demo();
        Demo d2 = new Demo();
        Demo d3 = new Demo();
        l2.add(d1);
        l2.add(d2);
        l2.add(d3);

        List l3 = new ArrayList();
        Demo d4 = new Demo();
        Demo d5 = new Demo();
        Demo d6 = new Demo();
        l3.add(d4);
        l3.add(d5);
        l3.add(d6);


        list.add(l2);
        list.add(l3);
        return list;
    }

    /**
     * 使用@RequestParam接收请求参数
     * 注意"/requestparam"后面没有占位符{}
     * 此时 http请求为：http://localhost:8080/home/requestparam?id=20
     * @return
     */
    @RequestMapping("/requestparam")
    @ResponseBody
    public String requestParam(@RequestParam("id") Long id) {
        System.out.println(id);
        return "接收到的id值为：" + id;
    }

    /**
     * 使用@RequestMapping的produces属性设置Content-Type，默诵的Content-Type为text/html
     * 此时返回Content-Type: application/json;charset=utf-8
     * 注：也可在类上的@RequestMapping中使用produces属性在全局层面进行设置
     * @param id
     * @return
     */
    @RequestMapping(value = "/requestparam2", produces = MediaType.APPLICATION_JSON_VALUE + " ;charset=utf-8")
    @ResponseBody
    public String requestParam2(@RequestParam("id") Long id) {
        System.out.println(id);
        return "接收到的id值为：" + id;
    }

    /**
     * 使用@PathVariable接收rest请求形式的参数
     * 注意"/pathvariable"后面有占位符{id}(不是{}),然后利用@PathVariable将其指定为id
     * 此时 http请求为：http://localhost:8080/home/pathvariable/20
     * @return
     */
    @RequestMapping("/pathvariable/{id}")
    @ResponseBody
    public String pathVariable(@PathVariable("id") Long id) {
        System.out.println(id);
        return "接收到的id值为：" + id;
    }

    /**
     * 使用@RequestHeader获取请求头的信息
     * 类似的注解还有：@CookieValue：获取cookie的值，
     * @param host
     * @return
     */
    @RequestMapping("/requestheader")
    @ResponseBody
    public String requestHeader(@RequestHeader("Host") String host) {
        System.out.println("host is :" + host);
        return "host is :" + host;
    }

    /**
     * 引入hibernate-validator的5.2.1.Final版本是ok的，但引入6.0.10.Final版本竟然不行！
     * 一个@Validated必须对应一个BindingResult
     * @param validatedDemo
     * @param result
     * @return
     */
    @RequestMapping(value = "/validated", method = RequestMethod.POST)
    @ResponseBody
    public ValidatedDemo validated(@Validated @RequestBody ValidatedDemo validatedDemo, BindingResult result) {
        System.out.println(result);
        // 如果校验发生了错误，则做下面的处理
        if (result.hasErrors()) {
            throw new RuntimeException(result.getAllErrors().get(0).getDefaultMessage());
        }
        return validatedDemo;
    }

}

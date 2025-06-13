package com.univ.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.univ.domain.FormDemo;

/**
 * 演示springmvc收集参数的方式
 *
 *
 * @author univ
 * 2022/7/12 4:19 下午
 */
@RestController
@RequestMapping("/param")
public class ParamCollectController {

    @GetMapping(value = "/noParam")
    public String noParam() {
        return "noParam";
    }

    /**
     * get方法
     *
     * 同名参数可直接获取
     *
     * @return
     */
    @RequestMapping(value = "/samename", method = RequestMethod.GET)
    @ResponseBody
    public String sameName(Long id, String name) {
        System.out.println("接收到的id值为:" + id);
        System.out.println("接收到的name值为:" + name);
        return "接收到的id值为：" + id;
    }

    /**
     * get方法
     *
     * 使用@RequestParam接收请求参数，将请求参数映射为这里的入参，在非同名参数时使用
     *
     * 示例请求：http://localhost:8080/param/requestparam?id=100
     * @return
     */
    @RequestMapping(value = "/requestparam", method = RequestMethod.GET)
    @ResponseBody
    public String requestParam(@RequestParam("id") Long idX) {
        System.out.println("接收到的id值为:" + idX);
        return "接收到的id值为：" + idX;
    }

    /**
     * get方法
     *
     * 使用@RequestMapping的produces属性设置Content-Type，默诵的Content-Type为text/html
     * 此时返回Content-Type: application/json;charset=utf-8
     * 注：也可在类上的@RequestMapping中使用produces属性在全局层面进行设置
     *
     * 示例请求：http://localhost:8080/param/requestparam2?id=100
     * @param idX
     * @return
     */
    @RequestMapping(value = "/requestparam2",
            produces = MediaType.APPLICATION_JSON_VALUE + " ;charset=utf-8",
            method = RequestMethod.GET)
    @ResponseBody
    public String requestParam2(@RequestParam("id") Long idX) {
        System.out.println("接收到的id值为：" + idX);
        return "接收到的id值为：" + idX;
    }

    /**
     * get方法
     *
     * 使用@PathVariable接收rest请求形式的参数
     * 注意"/pathvariable"后面有占位符{id}(不是{}),然后利用@PathVariable将其指定为id
     * 示例请求：http://localhost:8080/param/pathvariable/100
     *
     * @return
     */
    @RequestMapping(value = "/pathvariable/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String pathVariable(@PathVariable("id") Long id) {
        System.out.println("接收到的id值为：" + id);
        return "接收到的id值为：" + id;
    }

    /**
     * get方法
     *
     * 使用@RequestHeader获取请求头的信息
     * 类似的注解还有：@CookieValue：获取cookie的值，
     * @param host
     * @return
     */
    @RequestMapping(value = "/requestheader", method = RequestMethod.GET)
    @ResponseBody
    public String requestHeader(@RequestHeader("Host") String host) {
        System.out.println("host is :" + host);
        return "host is :" + host;
    }

    /**
     * get方法
     *
     * 混合入参
     *
     * 使用@RequestHeader获取请求头的信息
     * 类似的注解还有：@CookieValue：获取cookie的值
     *
     * 示例：http://localhost:8080/param/mix?name=zhangsan&id=100
     * @param host
     * @return
     */
    @RequestMapping(value = "/mix", method = RequestMethod.GET)
    @ResponseBody
    public String mixParam(@RequestHeader("Host") String host, String name, @RequestParam("id") Long idX) {
        System.out.println("host is :" + host + " name: " + name + " id:" + idX);
        return "host is :" + host;
    }

    /**
     * Get请求
     *
     * 对象形式
     * 由结果可知，入参中的name既被赋值给了入参name，也被赋值给了formDemo对象和formDemo2对象中的name属性；
     * 小结：感觉是，尽最大可能将每个对象中的属性进行赋值；
     *
     * 示例：http://localhost:8080/param/obj?name=zhangsan&age=100&age2=200&ids=1,2,3&succeded=true
     * @param name
     * @param formDemo
     * @return
     */
    @RequestMapping(value = "/obj", method = RequestMethod.GET)
    @ResponseBody
    public String obj(String name, FormDemo formDemo, @RequestParam("nameX") String nameXx, FormDemo formDemo2) {
        System.out.println("name is :" + name);// name is :zhangsan
        System.out.println("nameXx is :" + nameXx);// nameXx is :nameX
        // formDemo is :FormDemo{age=100, age2=200, name='zhangsan', succeded=true, succeded2=null, ids=[1, 2, 3], ids2=null, birthday=null}
        System.out.println("formDemo is :" + formDemo.toString());

        // formDemo2 is :FormDemo{age=100, age2=200, name='zhangsan', succeded=true, succeded2=null, ids=[1, 2, 3], ids2=null, birthday=null}
        System.out.println("formDemo2 is :" + formDemo2.toString());
        return "obj";
    }

    @PostMapping(value = "/json")
    public FormDemo p1(@RequestBody FormDemo formDemo) {
        System.out.println(formDemo);
        return formDemo;
    }

    @PostMapping(value = "/form-urlencoded")
    public FormDemo p2(FormDemo formDemo) {
        System.out.println(formDemo);
        return formDemo;
    }

    @PostMapping(value = "/file")
    public FormDemo p3(FormDemo formDemo) {
        System.out.println(formDemo.getFile().getOriginalFilename());
        return formDemo;
    }


}

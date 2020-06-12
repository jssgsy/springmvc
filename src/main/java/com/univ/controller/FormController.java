package com.univ.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.FormDemo;

/**
 * @author univ
 * @datetime 2019/1/2 7:00 PM
 * @description 演示spring mvc接收表单提交请求
 *
 * 结合页面请求查看更直观
 * form请求方式(enctype指定)：
 * 1. application/x-www-form-urlencoded:也是默认的方式;
 * 2. text/plain:一般不用，XML作为编码方式的远程调用规范；
 * 3. multipart/form-data:上传文件、图片时用； 参见FileController
 * 3. application/json:不是form表单的原生请求方式；一般通过ajax请求而来
 *
 */
@RestController
@RequestMapping("/form")
public class FormController {

    /**
     * 接收表单请求(Content-Type: application/x-www-form-urlencoded)
     * application/x-www-form-urlencoded是表单提交的原始形式。
     * @param formDemo 名字可任意取
     *  注：必须是对象形式，不能是String xWwwFormUrlencoded(String name, Integer age)，即使只有一个参数
     * @return
     * curl形式：
     *  curl -X POST -H 'content-type:application/x-www-form-urlencoded' -d '{"name":"aaa", "age":30}' 'localhost:8080/form/urlencoded'
     */
    @RequestMapping("/urlencoded")
    public String xWwwFormUrlencoded(FormDemo formDemo){
        System.out.println(formDemo);
        return "ok";
    }

    /**
     * 接收表单请求(Content-Type: application/json)
     * 注：
     *  1. application/json不是表单原始提交的方式，一般是通过ajax请求发出的，这里用curl模拟；
     *  2. spring mvc接收application/json请求是，@RequestBody可选
     * @param formDemo 名字任取
     * @return
     * curl -X POST -H 'content-type:application/json' -d '{"name":"bbb", "age":28}' 'localhost:8080/form/urlencoded'
     */
    @RequestMapping("/json")
    public String json(/*@RequestBody */FormDemo formDemo){
        System.out.println(formDemo);
        return "ok";
    }

    @RequestMapping("/basic")
    public String form(@RequestBody FormDemo formDemo) {
        System.out.println(formDemo);
        // 结果如下:
        /*"{\n"
                + "    \"age\":1,\n"
                + "    \"age2\":2,\n"
                + "    \"name\":\"univ\",\n"
                + "    \"succeded\":true,\n"
                + "    \"succeded2\":true,\n"
                + "    \"ids\":[\n"
                + "        1,\n"
                + "        2,\n"
                + "        3,\n"
                + "        4,\n"
                + "        5\n"
                + "    ],\n"
                + "    \"ids2\":[\n"
                + "        9,\n"
                + "        8,\n"
                + "        7,\n"
                + "        6\n"
                + "    ],\n"
                + "    \"birthday\":\"2019-01-02 19:01:23\"\n"
                + "}"*/
        return "ok";
    }
}

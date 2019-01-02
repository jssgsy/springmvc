package com.univ.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.univ.domain.FileDemo;

/**
 * @author univ
 * @datetime 2018/12/29 2:38 PM
 * @description 文件上传下载controller
 */

/*
核心元素：MultipartResolver接口
1. 实现文件上传，其实就是解析一个Mutipart请求。DispatchServlet自己并不负责去解析mutipart 请求，而是委托一个实现了MultipartResolver接口的类来解析mutipart请求,spring mvc已经提供了两个默认实现了MultipartResolver的类CommonsMultipartFile与StandardMultipartFile（需要servlet3.0的支持），这里以CommonsMultipartFile，其内部依赖于commons-fileupload组件；
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 最基本的文件上传
     * @param file
     * @param name 额外的参数
     * @param age 额外的参数
     * @return
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, String name, Integer age) {
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        return "ok";
    }

    /**
     * 当然也可以将上传的文件封装到bean中
     * 但注意：不能使用@RequestBody修饰fileDemo，因为@RequestBody是用来处理json字符串的，显然这里不是json字符串
     * @return
     */
    @RequestMapping("/upload2")
    public String upload2(FileDemo fileDemo) {
        System.out.println(fileDemo.getName());
        System.out.println(fileDemo.getAge());
        System.out.println(fileDemo.getFile().getName());
        System.out.println(fileDemo.getFile().getContentType());
        return "ok";
    }

    /**
     * 上传文件的同时附加其它很多参数
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload3")
    public String upload3(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println(file.getName());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(request.getParameter("name"));
        
        return "ok";
    }

}

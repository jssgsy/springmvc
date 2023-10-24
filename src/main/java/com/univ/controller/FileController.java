package com.univ.controller;

import com.univ.domain.FileDemo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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

    /**
     * 最基本的文件下载
     * @param request
     * @param response
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = "数据.txt";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename="
                + URLEncoder.encode(filename,"utf8"));
        // 往文件中写入数据
        response.getOutputStream().write("你好".getBytes());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    /**
     * 图片下载
     */
    @RequestMapping("/download/image")
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 模拟图片的二进制数据
        byte[] bytes = new byte[1024];
        response.setContentType("image/png");

        // 往文件中写入数据
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}

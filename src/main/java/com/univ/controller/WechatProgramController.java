package com.univ.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.PostConstruct;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;

/**
 * @author univ
 * @date 2020/6/17 3:13 PM
 * @description 微信小程序相关的接口
 */
@RestController
@RequestMapping("/wechat/program")
public class WechatProgramController {

    private RestTemplate restTemplate;

    /**
     * 小程序appId
     */
    private static final String APP_ID = "wx67f454d4dc1fe40c";

    /**
     * 小程序appSecret
     */
    private static final String APP_SECRET = "40c3577e62bdae0505731942e93ba0fa";

    /**
     * 小程序access_token获取
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={?}&secret={?}";

    /**
     * 上传临时素材
     * @see https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html
     */
    private static final String UPLOAD_TEMP_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token={?}&type=image";

    /**
     * 客服消息下发接口url
     * 下发消息有很多限制，需要仔细查看文档
     * @see https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Service_Center_messages.html#7
     */
    private static final String KEFU_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={?}";

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
    }

    @RequestMapping("/accesstoken/get")
    public String getAccessToken() {
        String body = restTemplate.exchange(ACCESS_TOKEN_URL, HttpMethod.GET, HttpEntity.EMPTY, String.class, APP_ID, APP_SECRET).getBody();
        JSONObject resultObj = JSON.parseObject(body);
        System.out.println("获取access_token-结果" + resultObj);
        return resultObj.getString("access_token");
    }

    /**
     * 本地文件上传成功版
     *
     * @param filePath    本地文件图片全路径，如
     * @param accessToken 通过getAccessToken()方法获取即可
     * @return
     */
    @RequestMapping("/material/temp/local")
    public String uploadTempMaterialFromLocal(String filePath, String accessToken) {
        //设置请求体，注意是LinkedMultiValueMap
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();

        //设置上传文件
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        data.add("media", fileSystemResource);

        //上传文件,设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.setContentLength(fileSystemResource.getFile().length());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data, httpHeaders);
        try {
            String resultJSON = restTemplate.postForObject(UPLOAD_TEMP_MATERIAL_URL, requestEntity, String.class, getAccessToken());
            System.out.println(resultJSON);
            return resultJSON;
        } catch (Exception e) {
            System.out.println(e);
        }

        return "ok";
    }

    /**
     * base64版(字节版)
     *
     * @return
     * @throws FileNotFoundException
     */
    @RequestMapping("/material/temp/base64")
    public String uploadTempMaterialFromBase64() throws FileNotFoundException {
        String imgStr = "iVBORw0KGgoAAAANSUhEUgAAAWgAAAFoAQAAAABSnlx4AAACCElEQVR42u2bO5KEMAxETTTH4KjmqByDCC22/tROsNHKVe2AAvw8iZAstTWN/jIaaND/Tt9Nxudufbw69msjOj/jft4p0EGXpyfzTI+7a7wc7862Pet4Ys7foFegh237oNuui49mj3OCvwvQC9HMDN/1AXpB2uxtdP4Z0AvQ0cBjdyWNzTMYf43foOvRIVM6f7t8zatAF6PTGPkRcZL0uPLBzNd6B3QxWhKi6cBk9n7CcuNLJ18Hujatm6iZ16tRNn8oTkFXp+WZw/J81JJUtlgpTkHXpxncSZOk4MXm4zsR6PK0lCxHM4mPlYWmcdgWd9DFadLUyLQhyY/Md11oAF2bVilBZiw/muMyyUEiMujadHeQX5pncxzOERl0ZdpFhRZ3142dup/m1KDL0+ynEpG9grmlEA3fAOjaNJmBN6M1IptoRKHeAV2YlsEGlhq02WmnljYEegHalYXXPusVjEVk0JXpW2ZiBZO+gWR50JVp09/Z8i0kvDM2i0QfFD/Q1WkpRPWEzL4BAV+WB12R1p4gPyGjdJrNg7tMQNemfdxJETKViDRog65Ou5Up9UMnocFOyEDXpkNndGwHsnSJUtYLujQdGi2DvLClQxY7TwO9Ap3z30/WGEAvRkvJ0tKe+o7IoCvT3hm9J+n92D1Jct0edGE6VCveDsS765XahkAXp/H/YtAr0T9O7zsLPl3McQAAAABJRU5ErkJggg==";
        //设置请求体，注意是LinkedMultiValueMap
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        BASE64Decoder decoder = new BASE64Decoder();
        String imgFilePath = "";
        try {
            //Base64解码
            byte[] b = new byte[0];
            try {
                b = decoder.decodeBuffer(imgStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            // //新生成的图片
            imgFilePath = "/Users/univ/Downloads/3331111.jpeg";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置上传文件
        FileSystemResource fileSystemResource = new FileSystemResource(imgFilePath);
        data.add("media", fileSystemResource);

        //上传文件,设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.setContentLength(fileSystemResource.getFile().length());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, httpHeaders);
        try {
            String resultJSON = restTemplate.postForObject(UPLOAD_TEMP_MATERIAL_URL, requestEntity, String.class, getAccessToken());
            System.out.println(resultJSON);
            return resultJSON;
        } catch (Exception e) {
            System.out.println("异常信息" + e);
        } finally {
            // 删除临时文件，并发情况下可能会覆盖
            if (fileSystemResource.exists()) {
                fileSystemResource.getFile().delete();
            }
        }
        return "ok";
    }

    /**
     * 发送客服消息，以文本消息为类， 图片消息类似
     * @return
     */
    @RequestMapping("/kefu/msg/send")
    public String sendKefuMsg(String content) {
        JSONObject jsonObject = new JSONObject();
        // 生效的提前是，需要访客先在客服消息页面发送消息，此时微信服务器才能响应
        jsonObject.put("touser", "oDH7s4s1-8nbpS6DRMtq-38b-rkE");
        jsonObject.put("msgtype", "text");
        JSONObject textBody = new JSONObject();
        textBody.put("content", content);
        jsonObject.put("text", textBody);

        HttpHeaders requestHeaders = new HttpHeaders();
        // json格式请求
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        // 构建请求参数
        HttpEntity httpEntity = new HttpEntity<>(jsonObject.toJSONString(), requestHeaders);
        String resultJSON = restTemplate.postForObject(KEFU_MSG_SEND_URL, httpEntity, String.class, getAccessToken());
        System.out.println("发送客服消息-结果：" + resultJSON);
        return "the msg sended to visitor is ：" + content;
    }

}

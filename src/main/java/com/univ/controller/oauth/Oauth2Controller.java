package com.univ.controller.oauth;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.univ.controller.util.RestTemplateUtil;

/**
 * @author univ
 * 2021/6/9 10:04 上午
 */
@RestController
@RequestMapping("oauth")
public class Oauth2Controller {

    private static final String CLIENT_ID = "6be4231b74f9ed0a9e86";

    private static final String CLIENT_SECRET = "410e64b6d124b5665257fbbd9a2098d49af0932d";

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

    @RequestMapping("/github/redirect")
    public String goToFormPage(@RequestParam("code") String code){
        System.out.println("从github获取到的code " + code);

        // 用code换令牌access_token
        HttpHeaders postHeaders = new HttpHeaders();
        // 设置成以json的格式来接收结果
        postHeaders.add("accept", "application/json");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("client_id", CLIENT_ID);
        paramMap.put("client_secret", CLIENT_SECRET);
        paramMap.put("code", code);
        HttpEntity<String> postEntity = new HttpEntity<String>(JSONObject.toJSONString(paramMap), postHeaders);
        String url = "https://github.com/login/oauth/access_token?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code;
        JSONObject result = restTemplate.postForObject(url, postEntity, JSONObject.class);
        System.out.println("从github请求access_token的结果为：" + result.toJSONString());

        // 用access_token换用户信息
        HttpHeaders getHeaders = new HttpHeaders();
        getHeaders.add("Authorization", "token " + result.getString("access_token"));
        HttpEntity getEntity = new HttpEntity<>(getHeaders);
        String userInfo = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, getEntity, String.class).getBody();
        System.out.println("从access_token换取用户的结果为：" + userInfo);

        // 注：实际使用中，这里需要跳转到应用的首页，且一般情况会显示授权用户github的头像
        return "home";
    }



}

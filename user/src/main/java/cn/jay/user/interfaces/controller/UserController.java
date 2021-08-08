package cn.jay.user.interfaces.controller;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @GetMapping(value = "/hello")
    public Result<String> hello() {
        return ResultUtils.success("Hello World");
    }

//    @PostMapping(value = "login")
//    public Result<String> login(Principal principal, @RequestParam String user, @RequestParam String psw) throws HttpRequestMethodNotSupportedException {
//        Map<String, String> map = new HashMap<>();
//        map.put("client_id", "user-service");
//        map.put("client_secret", "user559");
//        map.put("grant_type", "password");
//        map.put("username", user);
//        map.put("password", psw);
//        return custom(tokenEndpoint.postAccessToken(principal, map).getBody());
//    }
//
//    @Synchronized
//    private Result custom(OAuth2AccessToken accessToken) {
//        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
//        Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
//        data.put("accessToken", token.getValue());
//        if (token.getRefreshToken() != null) {
//            data.put("refreshToken", token.getRefreshToken().getValue());
//        }
//        return ResultUtils.success(data);
//    }
}

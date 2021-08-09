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

    @GetMapping(value = "/hello")
    public Result<String> hello() {
        return ResultUtils.success("Hello World");
    }
}

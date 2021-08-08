package cn.jay.user.interfaces.controller;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @GetMapping(value = "/index")
    public Result<String> hello() {
        return ResultUtils.success("Hello World");
    }

}

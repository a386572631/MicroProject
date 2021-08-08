package cn.jay.micro.interfaces.controller;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping(value = "hello")
public class HelloController {

    @GetMapping(value = "/index")
    public Result<String> hello() {
        return ResultUtils.success("Hello World");
    }

    @GetMapping(value = "/exception")
    public Result<String> exception() throws Exception {
        Integer a = 1 / 0;
        return null;
    }
}

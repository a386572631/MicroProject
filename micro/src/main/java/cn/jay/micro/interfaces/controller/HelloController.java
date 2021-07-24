package cn.jay.micro.interfaces.controller;

import common.dto.Result;
import common.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
public class HelloController {

    @GetMapping(value = "/index")
    public Result<String> hello() {
        return ResultUtils.success("Hello World");
    }
}

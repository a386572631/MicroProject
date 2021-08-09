package cn.jay.user.interfaces.controller;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import cn.jay.user.domain.entity.LoginDo;
import cn.jay.user.interfaces.dto.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    LoginDo loginDo;

    @GetMapping(value = "/hello")
    public Result<String> hello() {
        return ResultUtils.success("Hello World");
    }

    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestParam String userName, @RequestParam String password) {
        return loginDo.loginByAccount(userName, password);
//        return ResultUtils.success("123");
    }
}

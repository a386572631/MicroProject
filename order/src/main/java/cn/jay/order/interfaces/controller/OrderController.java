package cn.jay.order.interfaces.controller;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order")
@Api("Order")
public class OrderController {

    @GetMapping(value = "/hello")
    @ApiOperation(value = "hello")
    public Result<String> hello() {
        return ResultUtils.success("Hello order-service");
    }

}

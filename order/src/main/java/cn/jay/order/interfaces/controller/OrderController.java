package cn.jay.order.interfaces.controller;

import cn.jay.apis.ProviderService;
import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order")
@Api("Order")
public class OrderController {
    @DubboReference(version = "1.0")
    ProviderService providerService;

    @GetMapping(value = "/hello")
    @ApiOperation(value = "hello")
    public Result<String> hello() {
        return ResultUtils.success(providerService.test());
    }

}

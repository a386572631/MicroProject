package cn.jay.order.interfaces.controller;

import cn.jay.apis.ProviderService;
import cn.jay.order.infrastructure.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @DubboReference(version = "1.0")
    ProviderService providerService;

    @Autowired
    UserApi userApi;

    public void dubbo100() {
//        for (int i = 0; i < 100; i++) {
            providerService.test();
//        }
    }

    public void feign100() {
//        for (int i = 0; i < 100; i++) {
            userApi.hello2();
//        }
    }
}

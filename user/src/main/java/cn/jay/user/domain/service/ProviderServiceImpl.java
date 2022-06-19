package cn.jay.user.domain.service;

import cn.jay.apis.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0")
//@Service
public class ProviderServiceImpl implements ProviderService {

    public String test() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello dubbo!";
    }
}

package cn.jay.user.domain.service;

import cn.jay.apis.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0")
public class ProviderServiceImpl implements ProviderService {

    public String test() {
        return "Hello dubbo!";
    }
}

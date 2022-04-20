package cn.jay.order.infrastructure;

import cn.jay.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-service", fallback = UserFallback.class)
public interface UserApi {

    @GetMapping("/user/hello2")
    Result<String> hello2();
}

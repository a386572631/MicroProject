package cn.jay.user.infrastructure.feign.apis;

import cn.jay.common.dto.Result;
import cn.jay.user.infrastructure.feign.dto.AuthVo;
import cn.jay.user.infrastructure.feign.fallback.AuthFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-service", fallbackFactory = AuthFallback.class)
public interface AuthApi {

    @PostMapping("/oauth/token")
    Result<AuthVo> login(@RequestParam String client_id, @RequestParam String client_secret,
                         @RequestParam String grant_type, @RequestParam String username,
                         @RequestParam String password);
}

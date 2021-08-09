package cn.jay.gateway.feign;

import cn.jay.gateway.feign.dto.AuthVo;
import cn.jay.gateway.feign.fallback.AuthApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-service", fallbackFactory = AuthApiFallback.class)
public interface AuthApi {
    @GetMapping(value = "/oauth/check_token")
    AuthVo check(@RequestParam(value = "token") String token);
}

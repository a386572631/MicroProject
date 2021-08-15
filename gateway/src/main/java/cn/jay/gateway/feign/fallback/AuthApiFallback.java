package cn.jay.gateway.feign.fallback;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import cn.jay.gateway.feign.AuthApi;
import cn.jay.gateway.feign.dto.AuthVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthApiFallback implements FallbackFactory<AuthApi> {
    @Override
    public AuthApi create(Throwable throwable) {
        return new AuthApi() {
            @Override
            public Result<AuthVo> check(String token) {
                log.error(throwable.getMessage());
                return ResultUtils.error("验证失败");
            }
        };
    }
}

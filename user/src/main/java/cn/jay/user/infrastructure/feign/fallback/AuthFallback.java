package cn.jay.user.infrastructure.feign.fallback;

import cn.jay.common.constants.ResultState;
import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import cn.jay.user.infrastructure.feign.apis.AuthApi;
import cn.jay.user.infrastructure.feign.dto.AuthVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthFallback implements FallbackFactory<AuthApi> {
    @Override
    public AuthApi create(Throwable throwable) {
        return new AuthApi() {
            @Override
            public Result<AuthVo> login(String client_id, String client_secret, String grant_type, String username, String password) {
                log.error(throwable.getMessage());
                return ResultUtils.state(ResultState.LOGINFAILUNKNOW);
            }
        };
    }
}

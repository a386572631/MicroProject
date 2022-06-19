package cn.jay.order.infrastructure;

import cn.jay.common.dto.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallback implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable throwable) {
        return new UserApi() {
            @Override
            public Result<String> hello2() {
                return null;
            }
        };
    }
}

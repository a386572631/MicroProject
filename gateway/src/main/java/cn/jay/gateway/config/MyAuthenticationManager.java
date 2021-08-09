package cn.jay.gateway.config;

import cn.jay.gateway.feign.AuthApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * token 认证服务器
 */
@Component
@Slf4j
public class MyAuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    AuthApi authApi;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        // 认证管理器接收到token
        String token = authentication.getCredentials().toString();
        if (StringUtils.isBlank(token)) {
            return Mono.empty();
        }
        return Mono.just(authentication)
                .map(auth -> authApi.check(token))
                .log()
                .onErrorResume(e -> {
                    log.error("验证token时发生错误，错误类型为： {}，错误信息为： {}", e.getClass(), e.getMessage());
                    return Mono.empty();
                })
                .map(claims -> new UsernamePasswordAuthenticationToken(
                        claims.getUser_name(),
                        null,
                        Stream.of(claims.getAuthorities())
                                .peek(info -> log.info("权限信息： {}", info))
                                .map(it -> (List<String>)it)
                                .flatMap(it -> it.stream()
                                        .map(SimpleGrantedAuthority::new))
                                .collect(Collectors.toList())
                ));
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}

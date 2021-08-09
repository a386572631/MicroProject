package cn.jay.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Component
@Slf4j
public class TokenServerSecurityContextRepo implements ServerSecurityContextRepository {
    private final String BEARER = "Bearer ";

    @Autowired
    private ReactiveAuthenticationManager reactiveAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();

        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.startsWithIgnoreCase(authentication, BEARER)) {
            return Mono.empty();
        }
        // 截取凭证
        String credentials = authentication.length() <= BEARER.length() ?
                "" : authentication.substring(BEARER.length());
        log.info("credentials: " + credentials);

        // 把token返回给认证管理器来验证是否有效
        return reactiveAuthenticationManager.authenticate(
                new AbstractAuthenticationToken(null) {
                    @Override
                    public Object getCredentials() {
                        return credentials;
                    }

                    @Override
                    public Object getPrincipal() {
                        return null;
                    }
                }
        ).map(SecurityContextImpl::new);
    }

    private byte[] base64Decode(String value) {
        try {
            return Base64.getDecoder().decode(value);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}

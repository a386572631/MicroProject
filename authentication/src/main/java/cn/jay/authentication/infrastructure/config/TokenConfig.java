package cn.jay.authentication.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    private final String SIGNING_KEY = "test123";
    /**
     * 令牌存储策略
     */
    @Bean
    public TokenStore tokenStore() {
        // 内存方式生成普通令牌
//        return new InMemoryTokenStore();

        // JWT令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称密钥，资源服务器使用相同密钥验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}

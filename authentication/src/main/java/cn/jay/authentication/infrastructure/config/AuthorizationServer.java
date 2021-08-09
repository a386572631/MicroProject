package cn.jay.authentication.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
    /**
     * 配置客户端详情服务，从数据库获取用户详细信息（用户名和密码）进行校验
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                // 采用内存存储
                .inMemory()
                // 客户端id
                .withClient("user-client")
                // 客户端密钥
                .secret("user559")
                // 资源列表
                .resourceIds("res1")
                // 授权模式：授权码模式，简化模式，密码模式，客户端模式
                .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")
                // 允许的授权范围
                .scopes("all")
                // false 跳转到授权页面
                .autoApprove(false)
                // 回调地址
                .redirectUris("http://www.baidu.com");
    }

    /**
     * 配置令牌（token）的访问端点和令牌服务（tokenservices）
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 密码模式需要
                .authenticationManager(authenticationManager)
                // 授权码模式需要
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理服务
                .tokenServices(tokenServices())
                // 允许post提交
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .tokenEnhancer(new TokenEnhancer() {
                    @Override
                    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                        Map<String, Object> map = new HashMap<>();
                        map.put("principal", authentication.getPrincipal());
                        token.setAdditionalInformation(map);
                        return accessToken;
                    }
                });
    }

    /**
     * 配置令牌访问端点的安全策略
     * /oauth/authorize         授权端点
     * /oauth/token             令牌端点
     * /oauth/confirm_access    用户确认授权提交端点
     * /oauth/error             授权服务错误信息端点
     * /oauth/check_token       用于资源服务访问的令牌解析端点
     * /oauth/token_key         提供公有密钥的端点，如果使用JWT令牌的话
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // /oauth/token_key地址公开，不需登录即可取密钥
//                .tokenKeyAccess("permitAll()")
                // /oauth/check_token地址公开
                .checkTokenAccess("permitAll()")
                // 允许表单验证
                .allowFormAuthenticationForClients();
    }

    /**
     * 令牌管理服务
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        // 客户端信息服务
        services.setClientDetailsService(clientDetailsService);
        // 是否产生刷新令牌
        services.setSupportRefreshToken(true);
        // 令牌存储策略
        services.setTokenStore(tokenStore);

        // 设置令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        // 令牌默认有效期2小时
        services.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }

    /**
     * 授权码服务
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        // 设置授权码模式的授权码
        return new InMemoryAuthorizationCodeServices();
    }

}

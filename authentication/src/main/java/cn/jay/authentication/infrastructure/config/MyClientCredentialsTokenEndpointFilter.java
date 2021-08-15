package cn.jay.authentication.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Slf4j
public class MyClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {

    private final AuthorizationServerSecurityConfigurer configurer;

    private AuthenticationEntryPoint authenticationEntryPoint;

    public MyClientCredentialsTokenEndpointFilter(AuthorizationServerSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void afterPropertiesSet() {
        setAuthenticationFailureHandler((request, response, exception) ->
                authenticationEntryPoint.commence(request, response, exception));

        setAuthenticationSuccessHandler((request, response, authentication) -> {
            log.info("successHandler");
        });
    }

    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return configurer
                .and().getSharedObject(AuthenticationManager.class);
    }
}

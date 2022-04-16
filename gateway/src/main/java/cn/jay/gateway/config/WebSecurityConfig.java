package cn.jay.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@Order(2)
public class WebSecurityConfig {

    private static final String[] permitUrls = {
            "/user-service/user/login",
            "/*/swagger-ui.html",
            "/swagger-ui.html",
            "/swagger-resources/configuration/security",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/auth/v2/api-docs",
            "/sys/v2/api-docs",
            "/v2/api-docs",
            "/webjars/**",
            "/doc.html"
    };

    @Autowired
    TokenServerSecurityContextRepo tokenServerSecurityContextRepo;

    @Autowired
    MyHttpBasicServerAuthenticationEntryPoint myHttpBasicServerAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .securityContextRepository(tokenServerSecurityContextRepo)
                .authorizeExchange()
                .anyExchange().permitAll().and()
//                .pathMatchers("/user-service/**").permitAll()
                 // 白名单
//                .pathMatchers(this.permitUrls).permitAll()
//                .anyExchange().authenticated().and()
//                .httpBasic().authenticationEntryPoint(myHttpBasicServerAuthenticationEntryPoint).and()
                .formLogin()
                // 鉴权成功
//                .authenticationSuccessHandler(authenticationSuccessHandler)
                // 登陆验证失败
//                .authenticationFailureHandler(authenticationFailHandler)
                // 未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口
                .and().exceptionHandling().authenticationEntryPoint(myHttpBasicServerAuthenticationEntryPoint);
        return http.build();
    }
}

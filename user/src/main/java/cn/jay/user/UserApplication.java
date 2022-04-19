package cn.jay.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cn.jay.*"})
@SpringCloudApplication
@EnableFeignClients
@MapperScan(basePackages = {"cn.jay.*.domain.repository.mapper"})
@EnableDubbo(scanBasePackages = {"cn.jay.user.domain.service"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}

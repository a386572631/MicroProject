package cn.jay.gateway.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthVo {
    private List<String> aud;

    private String user_name;

    private List<String> scope;

    private Long exp;

    private List<String> authorities;

    private String jti;

    private String client_id;
}

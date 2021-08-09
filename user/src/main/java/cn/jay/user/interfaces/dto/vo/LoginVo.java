package cn.jay.user.interfaces.dto.vo;

import lombok.Data;

@Data
public class LoginVo {
    private String access_token;

    private String user_name;

    private String refresh_token;
}

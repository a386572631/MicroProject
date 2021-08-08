package cn.jay.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ResultState {
    SUCCESS("1", "请求成功"),
    FAIL("0", "请求失败"),
    LOGINSUCCESS("10000", "登录成功"),
    LOGINFAIL("10001", "用户名或密码错误"),
    LOGINFAIL_NOUSER("10002", "用户已失效"),
    // 预留
    LOGINFAILUNKNOW("10009", "登录失败");

    private String code;
    private String message;

    public ResultState of(String code) {
        Objects.requireNonNull(code);
        return Stream.of(values()).filter(item -> Objects.equals(item.getCode(), code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + "【不支持的类型】"));
    }
}

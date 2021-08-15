package cn.jay.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    UNAUTHENTICATION("10003", "未登录授权"),
    TOKENINVALID("10004", "TOKEN无效"),
    TOKENSUCCESS("10005", "TOKEN校验成功"),
    TOKENFAIL("10006", "TOKEN异常"),
    CLIENTERROR("10008", "client_id或client_secret错误"),
    LOGINFAILUNKNOW("10009", "登录失败");

    private String code;
    private String message;

    public static ResultState of(String code) {
        Objects.requireNonNull(code);
        return Stream.of(values()).filter(item -> Objects.equals(item.getCode(), code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + "【不支持的类型】"));
    }
}

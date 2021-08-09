package cn.jay.user.domain.entity;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import cn.jay.user.infrastructure.feign.apis.AuthApi;
import cn.jay.user.infrastructure.feign.dto.AuthVo;
import cn.jay.user.interfaces.dto.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDo {

    @Autowired
    AuthApi loginApi;

    public Result<LoginVo> loginByAccount(String userName, String password) {
        Result<AuthVo> login = loginApi.login("user-client", "user559", "password", userName, password);
        if (!"10000".equals(login.getCode())) {
            return ResultUtils.error("[" + login.getCode() + "]" + login.getMessage());
        }
        AuthVo data = login.getData();
        LoginVo bean = new LoginVo();
        bean.setAccess_token(data.getAccess_token());
        bean.setRefresh_token(data.getRefresh_token());
        bean.setUser_name(userName);
        return ResultUtils.success(bean);
    }
}

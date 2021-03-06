package cn.jay.authentication.interfaces.aop;

import cn.jay.common.constants.ResultState;
import cn.jay.common.dto.Result;
import cn.jay.common.utils.MyUtils;
import cn.jay.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
@Slf4j
public class AuthTokenAspect {
    /// @Around是可以改变controller返回值的
    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Result response = new Result();
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (InvalidGrantException | InternalAuthenticationServiceException e) {
            log.error(e.getMessage());
            return this.returnResponse(ResultUtils.state(ResultState.LOGINFAIL));
        }
        if (proceed != null) {
            ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>)proceed;
            OAuth2AccessToken body = responseEntity.getBody();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                response = ResultUtils.state(ResultState.LOGINSUCCESS, body);
            } else {
                log.error("error:{}", responseEntity.getStatusCode());
                response = ResultUtils.state(ResultState.LOGINFAILUNKNOW);
            }
        }
        return this.returnResponse(response);
    }

    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint.checkToken(..))")
    public Object handleCheckToken(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (InvalidTokenException e) {
            log.info("e.getOAuth2ErrorCode(): " + e.getOAuth2ErrorCode());
            log.error("e.getMessage(): " + e.getMessage());
            return MyUtils.objectToMap(ResultUtils.state(ResultState.TOKENINVALID));
        }
        Result response = proceed != null ? ResultUtils.state(ResultState.TOKENSUCCESS, proceed)
                : ResultUtils.state(ResultState.TOKENFAIL);
        return MyUtils.objectToMap(response);
    }

    private <T> ResponseEntity returnResponse(T response) {
        return ResponseEntity
                .status(200)
                .body(response);
    }
}

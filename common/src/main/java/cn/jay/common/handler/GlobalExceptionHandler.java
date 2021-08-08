package cn.jay.common.handler;

import cn.jay.common.dto.Result;
import cn.jay.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> handlerException(Exception e){
        log.error("[handleException]: " + e.getMessage());
        return ResultUtils.error("0", "Throw Default Exception~~~");
    }

}

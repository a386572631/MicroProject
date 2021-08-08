package cn.jay.common.utils;

import cn.jay.common.dto.Result;

public class ResultUtils<T> {

    public static <T> Result success(T data) {
        Result result = new Result(1, "请求成功", data);
        return result;
    }

    public static Result error(String errorMsg) {
        Result result = new Result(0, errorMsg);
        return result;
    }

    public static Result error(Integer code, String errorMsg) {
        Result result = new Result(code, errorMsg);
        return result;
    }
}

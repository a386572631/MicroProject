package cn.jay.common.utils;

import cn.jay.common.constants.ResultState;
import cn.jay.common.dto.Result;

public class ResultUtils<T> {

    public static <T> Result success(T data) {
        Result result = new Result("1", "请求成功", data);
        return result;
    }

    public static Result error(String errorMsg) {
        Result result = new Result("0", errorMsg);
        return result;
    }

    public static Result error(String code, String errorMsg) {
        Result result = new Result(code, errorMsg);
        return result;
    }

    public static <T> Result state(ResultState state) {
        Result result = new Result(state.getCode(), state.getMessage());
        return result;
    }

    public static <T> Result state(ResultState state, T data) {
        Result result = new Result(state.getCode(), state.getMessage(), data);
        return result;
    }
}

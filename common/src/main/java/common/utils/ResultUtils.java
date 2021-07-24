package common.utils;

import common.dto.Result;

public class ResultUtils<T> {

    public static <T> Result success(T data) {
        Result result = new Result(1, "请求成功", data);
        return result;
    }

    public static Result error(String errorMsg) {
        Result result = new Result(0, errorMsg);
        return result;
    }
}

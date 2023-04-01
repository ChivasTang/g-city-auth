package com.g.city.auth.rest.req;

import com.g.city.auth.constant.RouterConstants;
import lombok.Data;

@Data
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;
    private String path;

    public ApiResult() {
        this.timestamp = System.currentTimeMillis();
        this.path = RouterConstants.INDEX_ROUTER_MAKER;
    }

    public static <T> ApiResult<T> success(T data, String path) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(ResultCode.SUCCESS_RESULT.getCode());
        apiResult.setMessage(ResultCode.SUCCESS_RESULT.getMessage());
        apiResult.setPath(path);
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(ResultCode.SUCCESS_RESULT.getCode());
        apiResult.setMessage(ResultCode.SUCCESS_RESULT.getMessage());
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> fail(ResultCode resultCode) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(resultCode.getCode());
        apiResult.setMessage(resultCode.getMessage());
        return apiResult;
    }
}

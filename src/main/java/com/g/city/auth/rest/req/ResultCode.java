package com.g.city.auth.rest.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    INVALID_TOKEN(2001, "访问令牌不合法"),
    ACCESS_DENIED(2003, "没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),


    LOGIN_FAILED_USERNAME_NOT_INPUT(2101, "请输入用户名。"),
    LOGIN_FAILED_PASSWORD_NOT_INPUT(2102, "请输入密码。"),
    LOGIN_FAILED_USERNAME_NOT_EXIST(2103, "未找到该用户，请检查并重新输入。"),
    LOGIN_FAILED_USERNAME_PASSWORD_NOT_VALIDATED(2104, "用户名和密码不匹配，请检查并重新输入。"),

    REGISTER_FAILED_USERNAME_NOT_INPUT(2111, "请填写用户名。"),
    REGISTER_FAILED_PASSWORD_NOT_INPUT(2112, "请填写密码。"),
    REGISTER_FAILED_PASSWORD_NOT_CONFIRMED(2113, "密码不一致。"),
    REGISTER_FAILED_USERNAME_EXISTED(2114, "该用户已经存在。"),
    REGISTER_FAILED_NOT_SAVED(2115, "该用户数据入库失败。"),

    SUCCESS_RESULT(200, null);

    private final int code;
    private final String message;
}

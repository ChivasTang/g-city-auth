package com.g.city.auth.service;

import com.g.city.auth.dto.UserLogin;
import com.g.city.auth.rest.req.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserLoginService {
    ApiResult<Object> login(HttpServletRequest request, HttpServletResponse response, UserLogin userLogin);
}

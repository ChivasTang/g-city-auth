package com.g.city.auth.service;

import com.g.city.auth.dto.UserRegister;
import com.g.city.auth.rest.req.ResultCode;
import jakarta.servlet.http.HttpServletRequest;

public interface UserRegisterService {
    ResultCode register(HttpServletRequest request, UserRegister userRegister);
}

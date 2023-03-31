package com.g.city.auth.service;

import com.g.city.auth.dto.UserRegister;
import com.g.city.auth.rest.req.ResultCode;

public interface UserRegisterService {
    ResultCode register(UserRegister userRegister);
}

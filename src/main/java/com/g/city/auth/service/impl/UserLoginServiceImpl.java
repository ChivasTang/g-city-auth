package com.g.city.auth.service.impl;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.dto.UserLogin;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.rest.req.ResultCode;
import com.g.city.auth.service.JwtTokenService;
import com.g.city.auth.service.UserLoginService;
import com.g.city.auth.service.UserMstService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UserMstService userMstService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenService jwtTokenService;

    @Override
    public ApiResult<Object> login(HttpServletRequest request, HttpServletResponse response, UserLogin userLogin) {
        if (userLogin == null || userLogin.getUsername() == null || userLogin.getUsername().isBlank()) {
            return ApiResult.fail(ResultCode.LOGIN_FAILED_USERNAME_NOT_INPUT);
        }
        if (userLogin.getPassword() == null) {
            return ApiResult.fail(ResultCode.LOGIN_FAILED_PASSWORD_NOT_INPUT);
        }
        final String username = userLogin.getUsername();

        final UserDetails userDetails = userMstService.loadUserByUsername(username);
        if (userDetails == null) {
            return ApiResult.fail(ResultCode.LOGIN_FAILED_USERNAME_NOT_EXIST);
        }
        final String rawPassword = userLogin.getPassword();
        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            return ApiResult.fail(ResultCode.LOGIN_FAILED_USERNAME_PASSWORD_NOT_VALIDATED);
        }
        final String token = jwtTokenService.authenticate(request, response, userDetails);
        if(request.getRequestURI().equals(RouterConstants.TOKEN_ROUTER_MARKER)){
            userLogin.setToken(token);
        }
        userLogin.setUserId(userDetails.getUsername());
        userLogin.setPassword(null);
        return ApiResult.success(userLogin);
    }
}

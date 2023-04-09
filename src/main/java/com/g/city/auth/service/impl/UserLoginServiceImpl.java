package com.g.city.auth.service.impl;

import com.g.city.auth.dto.UserLogin;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.rest.req.ResultCode;
import com.g.city.auth.service.AccHistService;
import com.g.city.auth.service.JwtTokenService;
import com.g.city.auth.service.UserLoginService;
import com.g.city.auth.service.UserMstService;
import com.g.city.auth.util.RequestUtils;
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

    @Resource
    private AccHistService accHistService;

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
        final String jwtToken = jwtTokenService.authenticate(request, response, userDetails);
        final String userId = userDetails.getUsername();
        userLogin.setUserId(userId);
        if (RequestUtils.isTokenUrl(request)) {
            userLogin.setToken(jwtToken);
        }
        userLogin.setUsername(username);
        userLogin.setPassword(null);
        accHistService.save(request, userId);
        return ApiResult.success(userLogin);
    }
}

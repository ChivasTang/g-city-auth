package com.g.city.auth.rest;

import com.g.city.auth.constant.AppConstants;
import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.dto.UserLogin;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.service.UserLoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Resource
    private UserLoginService userLoginService;

    @PostMapping({RouterConstants.LOGIN_ROUTER_MARKER})
    @ResponseBody
    public ApiResult<Object> login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserLogin userLogin) {
        return userLoginService.login(request, response, userLogin);
    }

    @PostMapping({RouterConstants.LOGIN_DEFAULT_ROUTER_MARKER})
    @ResponseBody
    public ApiResult<Object> login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        final UserLogin userLogin = new UserLogin();
        userLogin.setUsername(StringUtils.isEmpty(username) ? request.getParameter(AppConstants.USERNAME_MARKER) : username);
        userLogin.setPassword(StringUtils.isEmpty(password) ? request.getParameter(AppConstants.PASSWORD_MARKER) : password);
        return userLoginService.login(request, response, userLogin);
    }

}

package com.g.city.auth.rest;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.dto.UserLogin;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.service.UserLoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({RouterConstants.TOKEN_ROUTER_MARKER})
public class TokenController {
    @Resource
    private UserLoginService userLoginService;

    @PostMapping
    @ResponseBody
    public ApiResult<Object> refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody final UserLogin userLogin) {
        return userLoginService.login(request, response, userLogin);
    }
}

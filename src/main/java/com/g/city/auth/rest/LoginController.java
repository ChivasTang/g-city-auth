package com.g.city.auth.rest;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.dto.UserLogin;
import com.g.city.auth.rest.req.ApiResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({RouterConstants.LOGIN_ROUTER_MARKER, RouterConstants.LOGIN_DEFAULT_ROUTER_MARKER})
public class LoginController {

    @PostMapping
    @ResponseBody
    public ApiResult<Object> login(@RequestBody UserLogin userLogin) {
        return ApiResult.success(userLogin);
    }
}

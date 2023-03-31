package com.g.city.auth.rest;

import com.g.city.auth.constant.AppConstants;
import com.g.city.auth.dto.UserRegister;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.rest.req.ResultCode;
import com.g.city.auth.service.UserRegisterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({AppConstants.REGISTER_ROUTER_MARKER})
public class RegisterController {
    @Resource
    private UserRegisterService userRegisterService;

    @PostMapping
    @ResponseBody
    public ApiResult<Object> register(@RequestBody UserRegister userRegister) {
        final ResultCode resultCode = userRegisterService.register(userRegister);
        return resultCode.getCode() == 200
                ? ApiResult.success(userRegister, AppConstants.LOGIN_ROUTER_MARKER)
                : ApiResult.fail(resultCode);
    }
}

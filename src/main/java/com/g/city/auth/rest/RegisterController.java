package com.g.city.auth.rest;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.dto.UserRegister;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.rest.req.ResultCode;
import com.g.city.auth.service.UserRegisterService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({RouterConstants.REGISTER_ROUTER_MARKER})
public class RegisterController {
    @Resource
    private UserRegisterService userRegisterService;

    @PostMapping
    @ResponseBody
    public ApiResult<Object> register(HttpServletRequest request, @RequestBody UserRegister userRegister) {
        final ResultCode resultCode = userRegisterService.register(request, userRegister);
        return resultCode.getCode() == 200
                ? ApiResult.success(userRegister, RouterConstants.LOGIN_ROUTER_MARKER)
                : ApiResult.fail(resultCode);
    }
}

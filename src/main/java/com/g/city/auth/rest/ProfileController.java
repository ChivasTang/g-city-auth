package com.g.city.auth.rest;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.rest.req.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({RouterConstants.PROFILE_ROUTER_MARKER})
public class ProfileController {

    @PostMapping
    @ResponseBody
    public ApiResult<Object> profile() {
        return ApiResult.success("This profile data...");
    }

}

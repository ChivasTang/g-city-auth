package com.g.city.auth.rest;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.rest.req.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({RouterConstants.INDEX_ROUTER_MAKER})
public class IndexController {

    @GetMapping
    public ApiResult<Object> index() {
        return ApiResult.success("This is index page...");
    }
}

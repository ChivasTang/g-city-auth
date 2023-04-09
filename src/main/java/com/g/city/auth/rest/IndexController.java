package com.g.city.auth.rest;

import com.g.city.auth.constant.RouterConstants;
import com.g.city.auth.rest.req.ApiResult;
import com.g.city.auth.service.AccHistService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({RouterConstants.INDEX_ROUTER_MAKER})
public class IndexController {

    @Resource
    private AccHistService accHistService;

    @GetMapping
    public ApiResult<Object> index(HttpServletRequest request) {
        accHistService.save(request, null);
        return ApiResult.success("This is index page...");
    }
}

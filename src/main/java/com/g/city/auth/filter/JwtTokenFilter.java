package com.g.city.auth.filter;

import com.g.city.auth.service.JwtTokenService;
import com.g.city.auth.util.RequestUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // if Login action
        if (RequestUtils.isLoginUrl(request) || RequestUtils.isTokenUrl(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (RequestUtils.isRegisterUrl(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (RequestUtils.hasBearer(request)) {
            jwtTokenService.doFilterJWT(request, response);
        }
        filterChain.doFilter(request, response);
    }
}

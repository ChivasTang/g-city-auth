package com.g.city.auth.filter;

import com.g.city.auth.util.RequestUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {
    private final UserDetailsService userMstService;

    public LoginFilter(UserDetailsService userMstService) {
        this.userMstService = userMstService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (RequestUtils.isLoginRequest(request)) {
            doFilterLogin(request, response, filterChain);
        }
        filterChain.doFilter(request, response);
    }

    private void doFilterLogin(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // set user details on spring security context
        String username = request.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            filterChain.doFilter(request, response);
            return;
        }
        final UserDetails userDetails = userMstService.loadUserByUsername(username);
        if (userDetails == null) {
            filterChain.doFilter(request, response);
            return;
        }
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

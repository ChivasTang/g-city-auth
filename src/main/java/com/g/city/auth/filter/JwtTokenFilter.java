package com.g.city.auth.filter;

import com.g.city.auth.constant.AppConstants;
import com.g.city.auth.service.JwtTokenService;
import com.g.city.auth.service.UserMstService;
import com.g.city.auth.util.RequestUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserMstService userMstService;
    private final JwtTokenService jwtTokenService;

    private final PasswordEncoder passwordEncoder;

    public JwtTokenFilter(final UserMstService userMstService, final JwtTokenService jwtTokenService, final PasswordEncoder passwordEncoder) {
        this.userMstService = userMstService;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // if Login action
        if (RequestUtils.isLoginUrl(request)) {
            doFilterLogin(request, response);
        } else if (RequestUtils.hasBearer(request)) {
            doFilterJWT(request, response);
        }
        filterChain.doFilter(request, response);
    }

    private void doFilterJWT(HttpServletRequest request, HttpServletResponse response) {
        final String requestTokenHeader = request.getHeader(HttpHeaders.WWW_AUTHENTICATE);
        if (requestTokenHeader != null && requestTokenHeader.length() > 6 && requestTokenHeader.startsWith(AppConstants.BEARER_PREFIX_MARKER.trim())) {
            final String authToken = requestTokenHeader.substring(6).trim();
            final String userId = jwtTokenService.getUserId(authToken);
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                final UserDetails userDetails = userMstService.loadUserByUserId(userId);
                if (jwtTokenService.validate(authToken, userDetails)) {
                    jwtTokenService.authenticate(request, response, userDetails);
                }
            }
        }
    }

    private void doFilterLogin(HttpServletRequest request, HttpServletResponse response) {
        final String username = request.getParameter(AppConstants.USERNAME_MARKER);
        final String rawPassword = request.getParameter(AppConstants.PASSWORD_MARKER);
        if (!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = userMstService.loadUserByUsername(username);
            if (userDetails != null && passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
                jwtTokenService.authenticate(request, response, userDetails);
            }
        }
    }
}

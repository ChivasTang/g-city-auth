package com.g.city.auth.util;

import com.g.city.auth.constant.RouterConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class RequestUtils {
    private final static String BEARER_HEADER_PREFIX = "Bearer";

    public static boolean isLoginUrl(HttpServletRequest request) {
        final String url = request.getRequestURI();
        return (url.contains(RouterConstants.LOGIN_ROUTER_MARKER)
                || url.contains(RouterConstants.TOKEN_ROUTER_MARKER)
                || url.contains(RouterConstants.LOGIN_DEFAULT_ROUTER_MARKER))
                && StringUtils.equals(request.getMethod(), HttpMethod.POST.name());
    }

    public static boolean hasBearer(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader(HttpHeaders.WWW_AUTHENTICATE);
        return !StringUtils.isEmpty(requestTokenHeader)
                && requestTokenHeader.startsWith(BEARER_HEADER_PREFIX)
                && !StringUtils.isEmpty(requestTokenHeader.substring(BEARER_HEADER_PREFIX.length()));
    }

}

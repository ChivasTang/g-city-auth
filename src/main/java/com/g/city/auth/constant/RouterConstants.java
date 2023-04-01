package com.g.city.auth.constant;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class RouterConstants {
    public final static String APP_VERSION_NO = "v1";
    public final static String INDEX_ROUTER_MAKER = "/";

    public final static String LOGIN_DEFAULT_ROUTER_MARKER = "/login";
    public final static String LOGIN_ROUTER_MARKER = "/" + APP_VERSION_NO + "/api/auth/login";
    public final static String REGISTER_ROUTER_MARKER = "/" + APP_VERSION_NO + "/api/auth/register";


    public static final AntPathRequestMatcher INDEX_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.GET, INDEX_ROUTER_MAKER);
    public static final AntPathRequestMatcher LOGIN_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, LOGIN_ROUTER_MARKER);
    public static final AntPathRequestMatcher LOGIN_DEFAULT_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, LOGIN_DEFAULT_ROUTER_MARKER);
    public static final AntPathRequestMatcher REGISTER_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, REGISTER_ROUTER_MARKER);
}

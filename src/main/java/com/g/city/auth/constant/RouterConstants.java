package com.g.city.auth.constant;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class RouterConstants {
    public final static String APP_VERSION_NO = "v1";
    public final static String INDEX_ROUTER_MAKER = "/";
    public final static String CSS_ROUTER_MARKER = "/css/**";
    public final static String IMG_ROUTER_MARKER = "/img/**";
    public final static String JS_ROUTER_MARKER = "/js/**";
    public final static String LOGIN_DEFAULT_ROUTER_MARKER = "/login";
    public final static String LOGIN_ROUTER_MARKER = "/" + APP_VERSION_NO + "/api/auth/login";
    public final static String REGISTER_ROUTER_MARKER = "/" + APP_VERSION_NO + "/api/auth/register";
    public final static String TOKEN_ROUTER_MARKER = "/" + APP_VERSION_NO + "/api/auth/token";
    public final static String PROFILE_ROUTER_MARKER = "/" + APP_VERSION_NO + "/api/auth/profile";

    public static final AntPathRequestMatcher INDEX_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.GET, INDEX_ROUTER_MAKER);
    public static final AntPathRequestMatcher CSS_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.GET, CSS_ROUTER_MARKER);
    public static final AntPathRequestMatcher IMG_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.GET, IMG_ROUTER_MARKER);
    public static final AntPathRequestMatcher JS_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.GET, JS_ROUTER_MARKER);
    public static final AntPathRequestMatcher LOGIN_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, LOGIN_ROUTER_MARKER);
    public static final AntPathRequestMatcher LOGIN_DEFAULT_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, LOGIN_DEFAULT_ROUTER_MARKER);
    public static final AntPathRequestMatcher REGISTER_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, REGISTER_ROUTER_MARKER);
    public static final AntPathRequestMatcher TOKEN_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, TOKEN_ROUTER_MARKER);
    //public static final AntPathRequestMatcher PROFILE_MATCHER = AntPathRequestMatcher.antMatcher(HttpMethod.POST, PROFILE_ROUTER_MARKER);
}

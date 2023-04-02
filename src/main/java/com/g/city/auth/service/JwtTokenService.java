package com.g.city.auth.service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtTokenService {

    String authenticate(HttpServletRequest request, HttpServletResponse response, UserDetails userDetails);
    String generate(UserDetails userDetails);
    DecodedJWT decode(String jwtToken);
    boolean validate(String jwtToken, UserDetails userDetails);

    String getUserId(String jwtToken);
    Map<String, Claim> getClaims(String jwtToken);
}

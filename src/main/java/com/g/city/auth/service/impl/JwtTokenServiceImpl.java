package com.g.city.auth.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.g.city.auth.constant.AppConstants;
import com.g.city.auth.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Service
@Data
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService, Serializable {

    @Serial
    private static final long serialVersionUID = -6760024902080312640L;

    private final static String secret = "g-city.com";

    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    static  {
        algorithm = Algorithm.HMAC256(Base64.getEncoder().encodeToString(secret.getBytes()));
        verifier = JWT.require(algorithm).build();
    }

    @Override
    public String authenticate(HttpServletRequest request, HttpServletResponse response, UserDetails userDetails) {
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwtToken = generate(userDetails);
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, AppConstants.BEARER_PREFIX_MARKER + jwtToken);
        return jwtToken;
    }

    @Override
    public String generate(UserDetails userDetails) {
        final String userId = userDetails.getUsername();
        Instant issuedAt = OffsetDateTime.now().toInstant();
        Instant expiresAt = OffsetDateTime.now().plusDays(7L).toInstant();
        String jwtToken = JWT.create()
                .withJWTId(UUID.randomUUID().toString())//"jti" : JWT ID
                .withIssuer(userId)//"iss" : Issuer
                .withSubject(userId)//"sub" : Subject
                .withIssuedAt(issuedAt)//"iat" : Issued At
                .withNotBefore(issuedAt)//"nbf" : Not Before
                .withExpiresAt(expiresAt)//"exp" : Expiration Time
                .withClaim(userDetails.getUsername(), userDetails.getPassword())//private claims
                .sign(algorithm);
        log.debug("JwtTokenService - generate:" + jwtToken + "\n");
        return jwtToken;
    }

    @Override
    public DecodedJWT decode(String jwtToken) {
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(jwtToken);
            log.debug("JwtTokenService - decode: {}\n", jwt);
        } catch (SignatureVerificationException | AlgorithmMismatchException | JWTDecodeException |
                 TokenExpiredException | InvalidClaimException ex) {
            log.error("JwtTokenService - decode: {}\n", ex.getMessage());
        }
        return jwt;
    }

    @Override
    public boolean validate(String jwtToken, UserDetails userDetails) {
        final Claim claim = getClaims(jwtToken).get(userDetails.getUsername());
        log.debug("JwtTokenService - validate: {}\n", claim.toString());
        return StringUtils.equals(claim.asString(), userDetails.getPassword());
    }

    @Override
    public String getUserId(String jwtToken) {
        final String userId = decode(jwtToken).getSubject();
        log.debug("JwtTokenService - getUserId: {}\n", userId);
        return userId;
    }

    @Override
    public Map<String, Claim> getClaims(String jwtToken) {
        return decode(jwtToken).getClaims();
    }
}

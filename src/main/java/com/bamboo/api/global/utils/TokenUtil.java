package com.bamboo.api.global.utils;

import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.config.properties.JwtProperties;
import com.bamboo.api.global.enums.TokenTypeEnum;
import com.bamboo.api.global.exception.errors.CustomError;
import com.bamboo.api.global.exception.errors.codes.ErrorCodes;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private final JwtProperties jwtProperties;

    public ParseTokenDto getDataFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return new ParseTokenDto(claims.getIssuer(), claims.getSubject());
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new CustomError(ErrorCodes.TOKEN_FOREGED_ERROR);
        } catch (ExpiredJwtException e) {
            throw new CustomError(ErrorCodes.TOKEN_EXPIRED_ERROR);
        } catch (Exception e) {
            throw new CustomError(ErrorCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public String generateToken(User user, TokenTypeEnum tokenType) {
        return doGenerateToken(new HashMap<>(), user.getId(), tokenType.getType());
    }

    public String generateRefreshToken(User user, TokenTypeEnum tokenType) {
        return doGenerateToken(new HashMap<>(), user.getId(), tokenType.getType());
    }

    private String doGenerateToken(Map<String, Object> claims, String username, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }
}
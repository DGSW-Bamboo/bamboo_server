package com.bamboo.api.global.utils;

import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.exception.errors.CustomError;
import com.bamboo.api.global.exception.errors.codes.ErrorCodes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenUtil {
  private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  @Value("${jwt.secret}")
  private String secretKey;

  public ParseTokenDto getUsernameFromToken(String token){
    try{
      return getClaimFromToken(token);
    }catch(Exception ex){
      throw new CustomError(ErrorCodes.HANDLE_ACCESS_DENIED);
    }
  }

  public Date getExpirationDateFromToken(String token){
    return getAllClaimsFromToken(token).getExpiration();
  }

  public ParseTokenDto getClaimFromToken(String token) {
    final Claims claims = getAllClaimsFromToken(token);
    return new ParseTokenDto(claims.getIssuer(), claims.getSubject());
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secretKey).requireSubject("token").parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token){
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(User user){
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, user.getId(), "access-token");
  }

  public String generateRefreshToken(User user){
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, user.getId(), "refresh-token");
  }

  private String doGenerateToken(Map<String, Object> claims, String username, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuer(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
  }

  public Boolean validateAccessToken(String token, User user){
    final ParseTokenDto parseTokenDto = getUsernameFromToken(token);
    return (
            parseTokenDto.getSubject().equals(user.getId()) &&
                    !isTokenExpired(token) &&
                    parseTokenDto.getIssuer().equals("access-token")
    );
  }

  public Boolean validateRefreshToken(String refreshToken){
    final ParseTokenDto parseTokenDto = getUsernameFromToken(refreshToken);
    return (
            parseTokenDto.getIssuer().equals("refresh-token") &&
                    !isTokenExpired(refreshToken)
    );
  }
}
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

  public String getUsernameFromToken(String token){
    try{
      return getClaimFromToken(token, Claims::getSubject);
    }catch(Exception ex){
      throw new CustomError(ErrorCodes.HANDLE_ACCESS_DENIED);
    }
  }

  public Date getExpirationDateFromToken(String token){
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token){
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(User user){
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, user.getId());
  }

  private String doGenerateToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
  }

  public Boolean validateToken(String token, User user){
    final String username = getUsernameFromToken(token);
    return(username.equals(user.getId()) && !isTokenExpired(token));
  }
}
package com.bamboo.api.domain.token.service;

public interface TokenService {

  String tokenRenewal(String refreshToken);
}

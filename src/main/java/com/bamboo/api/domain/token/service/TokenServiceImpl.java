package com.bamboo.api.domain.token.service;

import com.bamboo.api.global.utils.TokenUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

  private final TokenUtil tokenUtil;

  @Override
  public String tokenRenewal(String refreshToken) {

    return null;
  }
}

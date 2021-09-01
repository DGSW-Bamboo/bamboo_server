package com.bamboo.api.domain.token.service;

import com.bamboo.api.global.exception.errors.CustomError;
import com.bamboo.api.global.exception.errors.codes.ErrorCodes;
import com.bamboo.api.global.utils.ParseTokenDto;
import com.bamboo.api.global.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

  private final TokenUtil tokenUtil;

  @Override
  public String tokenRenewal(String refreshToken) {

    ParseTokenDto a = this.tokenUtil.getUsernameFromToken(refreshToken);

    if (this.tokenUtil.validateRefreshToken(refreshToken)) throw new CustomError(ErrorCodes.TOKEN_VERIFY_ERROR);



    return null;
  }
}
package com.bamboo.api.domain.token.service;

import com.bamboo.api.domain.auth.service.MemberService;
import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.enums.TokenTypeEnum;
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
  private final MemberService memberService;

  @Override
  public String tokenRenewal(String refreshToken) {

    final ParseTokenDto parseToken = this.tokenUtil.getDataFromToken(refreshToken);

    User user = this.memberService.getUserFindById(parseToken.getSubject());

    return tokenUtil.generateToken(user, TokenTypeEnum.ACCESS_TOKEN);
  }
}
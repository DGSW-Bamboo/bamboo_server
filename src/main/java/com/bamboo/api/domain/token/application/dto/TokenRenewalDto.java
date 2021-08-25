package com.bamboo.api.domain.token.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TokenRenewalDto {

  @NotBlank(message = "refreshToken은 필수 입력값입니다")
  private String refreshToken;

  public TokenRenewalDto (String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
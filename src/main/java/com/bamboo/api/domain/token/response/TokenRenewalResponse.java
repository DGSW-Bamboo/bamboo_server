package com.bamboo.api.domain.token.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenRenewalResponse {

  private final String token;
}

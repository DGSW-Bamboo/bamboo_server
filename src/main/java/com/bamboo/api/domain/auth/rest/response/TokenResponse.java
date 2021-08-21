package com.bamboo.api.domain.auth.rest.response;

import com.bamboo.api.global.config.restTemplate.response.BaseTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TokenResponse extends BaseTemplate {

  private final TokenData data;

  public TokenResponse (int status, String message, TokenData data) {
    super(status, message);
    this.data = new TokenData(data);
  }

  @Getter
  @AllArgsConstructor
  public static class TokenData {

    private final String token;

    private final String refreshToken;

    public TokenData (TokenData data) {
      this.token = data.getToken();
      this.refreshToken = data.getRefreshToken();
    }
  }

}

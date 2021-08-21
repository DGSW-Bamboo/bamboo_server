package com.bamboo.api.domain.auth.response;

import com.bamboo.api.domain.models.User;
import lombok.Getter;

@Getter
public class MemberWithTokenResponse extends MemberResponse {

  private final String token;
//  private final String refreshToken;

  public MemberWithTokenResponse (User user, String token) {

    super(user);
    this.token = token;
  }

//  public MemberWithTokenResponse (String message, User user, String token, String refreshToken) {
//
//    super(message, user);
//    this.token = token;
//    this.refreshToken = refreshToken;
//  }
}
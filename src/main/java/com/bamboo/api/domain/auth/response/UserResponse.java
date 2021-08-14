package com.bamboo.api.domain.auth.response;

import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.common.response.HttpResponse;
import lombok.Getter;

@Getter
public class UserResponse extends HttpResponse {

  private final User user;

  public UserResponse(String message, User user) {

    super(message);
    this.user = user;
  }
}

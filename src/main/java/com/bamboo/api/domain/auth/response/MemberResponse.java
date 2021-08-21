package com.bamboo.api.domain.auth.response;

import com.bamboo.api.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

  private final User user;
}

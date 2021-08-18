package com.bamboo.api.domain.auth.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DodamLoginDto {

  private String code;

  DodamLoginDto(String code) {
    this.code = code;
  }
}

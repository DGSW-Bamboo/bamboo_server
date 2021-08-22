package com.bamboo.api.domain.auth.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DodamLoginDto {

  private String code;

  public DodamLoginDto (String code) {
    this.code = code;
  }
}

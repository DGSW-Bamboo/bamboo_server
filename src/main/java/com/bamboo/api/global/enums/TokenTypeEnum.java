package com.bamboo.api.global.enums;

import lombok.Getter;

@Getter
public enum TokenTypeEnum {

  ACCESS_TOKEN("ACCESS_TOKEN"),
  REFRESH_TOKEN("REFRESH_TOKEN");

  private final String type;

  TokenTypeEnum(String type) {
    this.type = type;
  }
}

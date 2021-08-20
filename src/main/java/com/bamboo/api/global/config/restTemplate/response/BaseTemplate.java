package com.bamboo.api.global.config.restTemplate.response;

import lombok.Getter;

@Getter
public class BaseTemplate {

  private final int status;
  private final String message;

  public BaseTemplate (int status, String message) {
    this.status = status;
    this.message = message;
  }
}

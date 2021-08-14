package com.bamboo.api.global.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponse {

  private final int status;
  private final String message;

  public HttpResponse (String message) {

    status = 200;
    this.message = message;
  }
}

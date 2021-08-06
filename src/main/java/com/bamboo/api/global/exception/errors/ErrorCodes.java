package com.bamboo.api.global.exception.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes {
  INVALID_INPUT_VALUE(400, "올바른 형식을 지켜주세요"),
  INVALID_TYPE_VALUE(400, "잘못된 타입입니다");


  private final int status;
  private final String message;

  ErrorCodes (int status, String message) {
    this.status = status;
    this.message = message;
  }
}

package com.bamboo.api.global.exception;

import com.bamboo.api.global.exception.errors.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExeptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
    log.error("타입 검증 오류 발생", e);
    final ErrorResponse errorResponse = ErrorResponse.of(ErrorCodes.INVALID_INPUT_VALUE, e.getBindingResult());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleBindException (BindException e) {
    log.error("바인딩 중 오류 발생", e);
    final ErrorResponse errorResponse = ErrorResponse.of(ErrorCodes.INVALID_INPUT_VALUE, e.getBindingResult());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


}

package com.bamboo.api.global.exception;

import com.bamboo.api.global.exception.errors.CustomError;
import com.bamboo.api.global.exception.errors.codes.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

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

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    log.error("enum에 바인딩 중 오류 발생", e);
    final ErrorResponse response = ErrorResponse.of(e);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    log.error("허용되지 않은 HTTP METHOD 요청", e);
    final ErrorResponse response = ErrorResponse.of(ErrorCodes.METHOD_NOT_ALLOWED);
    return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(AccessDeniedException.class)
  protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
    log.error("handleAccessDeniedException", e);
    final ErrorResponse response = ErrorResponse.of(ErrorCodes.HANDLE_ACCESS_DENIED);
    return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCodes.HANDLE_ACCESS_DENIED.getStatus()));
  }

  @ExceptionHandler(CustomError.class)
  protected ResponseEntity<ErrorResponse> handleBusinessException(final CustomError e) {
    log.error("handleEntityNotFoundException", e);
    final ErrorCodes errorCodes = e.getErrorCode();
    final ErrorResponse response = ErrorResponse.of(errorCodes);
    return new ResponseEntity<>(response, HttpStatus.valueOf(errorCodes.getStatus()));
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.error("handleEntityNotFoundException", e);
    final ErrorResponse response = ErrorResponse.of(ErrorCodes.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

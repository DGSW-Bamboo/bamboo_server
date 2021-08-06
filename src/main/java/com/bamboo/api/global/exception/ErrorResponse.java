package com.bamboo.api.global.exception;

import com.bamboo.api.global.exception.errors.ErrorCodes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private int status;
  private String message;
  private List<FieldErrors> errors;

  private ErrorResponse(final ErrorCodes errorCodes, final List<FieldErrors> errors) {
    this.status = errorCodes.getStatus();
    this.message = errorCodes.getMessage();
    this.errors = errors;
  }

  private ErrorResponse(final ErrorCodes code) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.errors = new ArrayList<>();
  }

  public static ErrorResponse of (final ErrorCodes errorCodes, final BindingResult bindingResult) {

    return new ErrorResponse(errorCodes, FieldErrors.of(bindingResult));
  }

  public static ErrorResponse of(final ErrorCodes errorCodes) {

    return new ErrorResponse(errorCodes);
  }

  public static ErrorResponse of(final ErrorCodes errorCodes, final List<FieldErrors> errors) {

    return new ErrorResponse(errorCodes, errors);
  }

  public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
    final String value = e.getValue() == null ? "" : e.getValue().toString();
    final List<FieldErrors> errors = FieldErrors.of(e.getName(), value, e.getErrorCode());
    return new ErrorResponse(ErrorCodes.INVALID_TYPE_VALUE, errors);
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FieldErrors {

    private String field;
    private String value;
    private String reason;

    private FieldErrors(final String field, final String value, final String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }

    public static List<FieldErrors> of (final String field, final String value, final String reason) {

      List<FieldErrors> fieldErrors = new ArrayList<>();
      fieldErrors.add(new FieldErrors(field, value, reason));
      return fieldErrors;
    }

    private static List<FieldErrors> of (final BindingResult bindingResult) {
      final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
              .map(error -> new FieldErrors(
                      error.getField(),
                      error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                      error.getDefaultMessage()
              )).collect(Collectors.toList());
    }
  }
}
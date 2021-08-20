package com.bamboo.api.global.exception.errors.codes;

import com.bamboo.api.global.exception.errors.CustomError;

public class PropertyIsUndefinedException extends CustomError {

  public PropertyIsUndefinedException(String message) {

    super(message, ErrorCodes.PROPERTY_IS_UNDEFINED_ERROR);
  }
}

package com.bamboo.api.domain.auth.exception;

import com.bamboo.api.global.exception.errors.codes.EntityNotFoundException;

public class UserMailNotFoundException extends EntityNotFoundException {

  public UserMailNotFoundException (String email) {
    super(email + " is not found");
  }
}

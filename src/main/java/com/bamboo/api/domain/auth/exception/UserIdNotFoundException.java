package com.bamboo.api.domain.auth.exception;

import com.bamboo.api.global.exception.errors.codes.EntityNotFoundException;

public class UserIdNotFoundException extends EntityNotFoundException {

  public UserIdNotFoundException (String id) {
    super(id + " is not found");
  }
}

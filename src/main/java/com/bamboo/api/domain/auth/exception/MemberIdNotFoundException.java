package com.bamboo.api.domain.auth.exception;

import com.bamboo.api.global.exception.errors.codes.EntityNotFoundException;

public class MemberIdNotFoundException extends EntityNotFoundException {

  public MemberIdNotFoundException(String id) {
    super(id + " is not found");
  }
}

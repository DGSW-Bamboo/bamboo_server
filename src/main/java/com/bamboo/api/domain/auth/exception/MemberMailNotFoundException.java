package com.bamboo.api.domain.auth.exception;

import com.bamboo.api.global.exception.errors.codes.EntityNotFoundException;

public class MemberMailNotFoundException extends EntityNotFoundException {

    public MemberMailNotFoundException(String email) {
        super(email + " is not found");
    }
}

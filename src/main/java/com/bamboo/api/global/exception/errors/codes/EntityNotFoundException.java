package com.bamboo.api.global.exception.errors.codes;

import com.bamboo.api.global.exception.errors.CustomError;

public class EntityNotFoundException extends CustomError {

    public EntityNotFoundException(String message) {

        super(message, ErrorCodes.ENTITY_NOT_FOUND);
    }
}

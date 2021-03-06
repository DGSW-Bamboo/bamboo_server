package com.bamboo.api.global.exception.errors.codes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes {
    INVALID_INPUT_VALUE(400, "올바른 형식을 지켜주세요"),
    INVALID_TYPE_VALUE(400, "잘못된 타입입니다"),
    METHOD_NOT_ALLOWED(405, "허용되지 않은 METHOD입니다"),
    HANDLE_ACCESS_DENIED(401, "권한이 없습니다"),
    INTERNAL_SERVER_ERROR(500, "서버 오류"),
    PROPERTY_IS_UNDEFINED_ERROR(500, "환경 변수 설정이 되어있지 않습니다"),

    TOKEN_FOREGED_ERROR(401, "잘못된 토큰입니다"),
    TOKEN_EXPIRED_ERROR(410, "만료된 토큰입니다"),

    ENTITY_NOT_FOUND(400, "Entity Not Found"),

    REST_SERVER_ERROR(401, "잘못된 코드 입니다");

    private final int status;
    private final String message;

    ErrorCodes(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

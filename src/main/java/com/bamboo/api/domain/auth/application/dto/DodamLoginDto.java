package com.bamboo.api.domain.auth.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DodamLoginDto {

    @NotBlank(message = "code는 필수 입력값입니다")
    private String code;

    public DodamLoginDto(String code) {
        this.code = code;
    }
}

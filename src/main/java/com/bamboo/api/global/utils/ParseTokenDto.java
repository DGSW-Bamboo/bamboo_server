package com.bamboo.api.global.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParseTokenDto {

    private final String issuer;

    private final String subject;
}
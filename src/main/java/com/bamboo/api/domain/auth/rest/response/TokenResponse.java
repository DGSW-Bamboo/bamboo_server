package com.bamboo.api.domain.auth.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private final String token;

    private final String refreshToken;
}

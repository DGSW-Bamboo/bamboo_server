package com.bamboo.api.domain.auth.rest.request;

import com.bamboo.api.global.utils.PropertyUtil;
import lombok.Getter;

@Getter
public class TokenRequest {

    public String code;
    public String clientId;
    public String clientSecret;

    public TokenRequest(String code) {
        this.code = code;
        this.clientId = PropertyUtil.getProperty("dodam.client-id");
        this.clientSecret = PropertyUtil.getProperty("dodam.client-secret");
    }
}

package com.bamboo.api.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("dodam")
public class DodamProperties {

    private String clientId;
    private String clientSecret;
}

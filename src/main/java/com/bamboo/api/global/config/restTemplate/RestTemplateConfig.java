package com.bamboo.api.global.config.restTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  private final RestTemplateBuilder restTemplateBuilder;

  private RestTemplate restTemplate (final String endpoint) {
    return restTemplateBuilder.rootUri(endpoint)
            .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
            .errorHandler(new RestTemplateErrorHandler())
            .setConnectTimeout(Duration.ofMinutes(3))
            .build();
  }

  @Bean
  public RestTemplate dodamAuthTemplate () {
    return this.restTemplate("http://dauth.b1nd.com/api");
  }

  @Bean
  public RestTemplate dodamOpenTemplate () {
    return this.restTemplate("http://open.dodam.b1nd.com/api");
  }
}
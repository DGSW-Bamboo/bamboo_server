package com.bamboo.api.global.config.restTemplate;

import com.bamboo.api.global.exception.errors.CustomError;
import com.bamboo.api.global.exception.errors.codes.ErrorCodes;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(@NonNull final ClientHttpResponse response) throws IOException {

    final HttpStatus status = response.getStatusCode();
    return !status.is2xxSuccessful();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {

    final String error = getErrorAsString(response);
    log.error("Headers: {}", response.getHeaders());
    log.error("Response failed : {}", error);

    throw new CustomError(ErrorCodes.REST_SERVER_ERROR);
  }

  private String getErrorAsString (@NonNull final ClientHttpResponse response) throws IOException {

    try(BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(response.getBody())))) {
      return  bufferedReader.readLine();
    }
  }
}

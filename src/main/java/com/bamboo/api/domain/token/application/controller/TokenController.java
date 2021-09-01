package com.bamboo.api.domain.token.application.controller;

import com.bamboo.api.domain.token.application.dto.TokenRenewalDto;
import com.bamboo.api.domain.token.service.TokenService;
import com.bamboo.api.global.common.response.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"Tokens"})
@RestController
@RequestMapping(value = "token")
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenService;

  @ApiOperation(value = "토큰 재 갱신", notes = "access-token이 만료되고 난 후 refresh토큰을 이용하여 갱신합니다")
  @PostMapping(value = "/")
  public ResponseEntity<Object> accessTokenRenewal (final @Valid @RequestBody TokenRenewalDto tokenRenewalDto) {

    this.tokenService.tokenRenewal(tokenRenewalDto.getRefreshToken());

    return ResponseHandler
            .generateResponse(
                    HttpStatus.OK,
                    "토큰 재발급 성공"
            );
  }
}
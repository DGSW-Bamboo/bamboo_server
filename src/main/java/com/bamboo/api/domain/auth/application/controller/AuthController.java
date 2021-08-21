package com.bamboo.api.domain.auth.application.controller;

import com.bamboo.api.domain.auth.application.dto.DodamLoginDto;
import com.bamboo.api.domain.auth.response.MemberResponse;
import com.bamboo.api.domain.auth.response.MemberWithTokenResponse;
import com.bamboo.api.domain.auth.service.MemberService;
import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.common.response.ResponseHandler;
import com.bamboo.api.global.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Auth"})
@RestController
@RequestMapping(value = "auth")
@RequiredArgsConstructor
public class AuthController {

  private final MemberService memberService;

  private final TokenUtil tokenUtil;

  @ApiOperation(value = "토큰 발급 밑 정보 갱신", notes = "dodam에서 로그인하고 발급받은 code를 넘겨받아 추가적인 인증과 회원 정보를 갱신하고 정보를 가져옵니다")
  @PostMapping(value = "/code")
  public ResponseEntity dodamLogin (final @RequestBody @Valid DodamLoginDto dodamLoginDto) {

    final User saveUser = memberService.save(dodamLoginDto.getCode());
    final String token = tokenUtil.generateToken(saveUser);
    final MemberWithTokenResponse memberWithTokenResponse = new MemberWithTokenResponse(saveUser, token);

    return ResponseHandler
            .generateResponse(
                    HttpStatus.OK,
                    "로그인과 토큰 발급 성공",
                    memberWithTokenResponse
            );
  }
}
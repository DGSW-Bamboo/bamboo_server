package com.bamboo.api.domain.auth.application.controller;

import com.bamboo.api.domain.auth.response.UserResponse;
import com.bamboo.api.domain.auth.service.FindUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Auth"})
@RestController
@RequestMapping(value = "auth")
@RequiredArgsConstructor
public class AuthController {

  private final FindUserService findUserService;

  @ApiOperation(value = "회원 조회", notes = "id로 특정 회원을 조회합니다")
  @GetMapping(value = "/find/{id}")
  public UserResponse getUserById (@PathVariable String id) {
    return new UserResponse("회원 조회 성공", findUserService.getUserFindById(id));
  }
//
//  @ApiOperation(value = "토큰 발급 밑 정보 갱신", notes = "dodam에서 로그인하고 발급받은 code를 넘겨받아 추가적인 인증과 회원 정보를 갱신하고 정보를 가져옵니다")
//  @GetMapping(value = "/code")
//  public UserResponse
}

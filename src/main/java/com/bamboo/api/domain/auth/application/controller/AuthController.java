package com.bamboo.api.domain.auth.application.controller;

import com.bamboo.api.domain.auth.response.UserResponse;
import com.bamboo.api.domain.auth.service.FindUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

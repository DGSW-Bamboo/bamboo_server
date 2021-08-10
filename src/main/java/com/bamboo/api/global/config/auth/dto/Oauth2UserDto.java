package com.bamboo.api.global.config.auth.dto;

import com.bamboo.api.domain.models.User;
import lombok.Getter;

@Getter
public class Oauth2UserDto {

  private String name;
  private String email;
  private String profileImage;

  public Oauth2UserDto(User user){
    this.name = user.getName();
    this.email = user.getEmail();
    this.profileImage = user.getProfileImage();
  }
}

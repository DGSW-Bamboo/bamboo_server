package com.bamboo.api.global.config.auth.dto;

import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.enums.AdminEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String id;
  private String name;
  private String email;
  private String picture;

  @Builder
  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String id, String name, String email, String picture) {
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.id = id;
    this.name = name;
    this.email = email;
    this.picture = picture;
  }

  public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
    // 추후 도담 로그인과 페이스북등 구분 (ofDodam, ofFacebook)

    return ofDodam(userNameAttributeName, attributes);
  }

  private static OAuthAttributes ofDodam(String userNameAttributeName, Map<String, Object> attributes) {
    return OAuthAttributes.builder()
            .id((String) attributes.get("uniqueId"))
            .name((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .picture((String) attributes.get("profileImage"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
  }

  public User toEntity() {
    return User.builder()
            .name(name)
            .email(email)
            .profileImage(picture)
            .build();
  }
}
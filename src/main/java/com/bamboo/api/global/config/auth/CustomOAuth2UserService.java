package com.bamboo.api.global.config.auth;

import com.bamboo.api.domain.auth.infrastructure.repository.UserRepositoryFindUser;
import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.config.auth.dto.OAuthAttributes;
import com.bamboo.api.global.config.auth.dto.Oauth2UserDto;
import com.bamboo.api.global.enums.AdminEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserRepositoryFindUser userRepositoryFindUser;
  private final HttpSession httpSession;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    // OAuth2UserService
    OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
    User user = saveOrUpdate(attributes);
    httpSession.setAttribute("user", new Oauth2UserDto(user));

    AdminEnum permission = AdminEnum.USER;

    if (user.getAdmin() != null) permission = user.getAdmin().getPermission();

    return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(permission.toString())),
            attributes.getAttributes(),
            attributes.getNameAttributeKey());
  }

  // 유저 생성 및 수정 서비스 로직
  private User saveOrUpdate(OAuthAttributes attributes){
    User user = userRepositoryFindUser.findByEmail(attributes.getEmail())
            .map(entity -> entity.update(attributes.getName(), attributes.getEmail(), attributes.getPicture()))
            .orElse(attributes.toEntity());
    return userRepositoryFindUser.save(user);
  }
}
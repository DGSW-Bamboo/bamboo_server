package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.auth.exception.MemberIdNotFoundException;
import com.bamboo.api.domain.auth.infrastructure.repository.MemberRepository;
import com.bamboo.api.domain.auth.rest.request.TokenRequest;
import com.bamboo.api.domain.auth.rest.response.DodamInfoResponse;
import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.config.restTemplate.RestTemplateConfig;
import com.bamboo.api.domain.auth.rest.response.TokenResponse;
import com.bamboo.api.global.enums.RoleEnum;
import com.bamboo.api.global.exception.errors.CustomError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

  private final MemberRepository memberRepository;
  private final RestTemplateConfig restTemplateConfig;

  @Override
  public User getUserFindById (String id) throws MemberIdNotFoundException {

    Optional<User> user = this.memberRepository.findById(id);

    if (user.isEmpty()) {
      throw new MemberIdNotFoundException(id);
    }

    return user.get();
  }

  @Override
  @Transactional
  public User save (final String code) {

    DodamInfoResponse.DodamInfoData dodamInfoData = this.getTokenInDodam(code).getData();
    Optional<User> optionalUser = this.memberRepository.findById(dodamInfoData.getUniqueId());

    if (optionalUser.isEmpty()) {

      User user = User.builder()
            .id(dodamInfoData.getUniqueId())
            .name(dodamInfoData.getName())
            .email(dodamInfoData.getEmail())
            .permission(RoleEnum.USER)
            .profileImage(dodamInfoData.getProfileImage())
            .build();
      return this.memberRepository.save(user);
    }

    User updateUser = optionalUser.get();
    updateUser.setName(dodamInfoData.getName());
    updateUser.setEmail(dodamInfoData.getEmail());
    updateUser.setProfileImage(dodamInfoData.getProfileImage());
    return this.memberRepository.save(updateUser);
  }

  private DodamInfoResponse getTokenInDodam (final String code) throws CustomError, NullPointerException {

    TokenRequest tokenRequest = new TokenRequest(code);
    TokenResponse tokenResponse = restTemplateConfig.dodamAuthTemplate().postForObject("/token", new HttpEntity<>(tokenRequest, null), TokenResponse.class);

    HttpHeaders headers = new HttpHeaders();
    headers.add("access-token", tokenResponse.getData().getToken());
    return restTemplateConfig.dodamOpenTemplate().exchange(
            "/user",
            HttpMethod.GET,
            new HttpEntity<String>("parameters", headers),
            DodamInfoResponse.class
    ).getBody();
  }
}
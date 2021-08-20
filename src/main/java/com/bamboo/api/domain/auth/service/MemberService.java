package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.auth.exception.MemberIdNotFoundException;
import com.bamboo.api.domain.auth.infrastructure.repository.MemberRepository;
import com.bamboo.api.domain.auth.rest.request.TokenRequest;
import com.bamboo.api.domain.auth.rest.response.DodamInfoResponse;
import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.config.restTemplate.RestTemplateConfig;
import com.bamboo.api.domain.auth.rest.response.TokenResponse;
import com.bamboo.api.global.exception.errors.CustomError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository MemberRepositoryFindUser;
  private final RestTemplateConfig restTemplateConfig;

  public User getUserFindById (String id) throws MemberIdNotFoundException {

    Optional<User> user = MemberRepositoryFindUser.findById(id);

    if (user.isEmpty()) {
      throw new MemberIdNotFoundException(id);
    }

    return user.get();
  }

  public void getTokenInDodam (String code) throws CustomError, NullPointerException {

    TokenRequest tokenRequest = new TokenRequest(code);
    TokenResponse tokenResponse = restTemplateConfig.dodamAuthTemplate().postForObject("/token", new HttpEntity<>(tokenRequest, null), TokenResponse.class);

    HttpHeaders headers = new HttpHeaders();
    headers.add("access-token", tokenResponse.getData().getToken());
    DodamInfoResponse dodamInfoResponse = restTemplateConfig.dodamOpenTemplate().exchange("/user", HttpMethod.GET, new HttpEntity<String>("parameters", headers), DodamInfoResponse.class).getBody();

  }
}

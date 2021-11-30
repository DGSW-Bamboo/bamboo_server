package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.auth.exception.MemberIdNotFoundException;
import com.bamboo.api.domain.auth.infrastructure.repository.MemberRepository;
import com.bamboo.api.domain.auth.rest.request.TokenRequest;
import com.bamboo.api.domain.auth.rest.response.DodamInfoResponse;
import com.bamboo.api.domain.auth.rest.response.TokenResponse;
import com.bamboo.api.domain.models.User;
import com.bamboo.api.global.config.restTemplate.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final static String TOKEN_HEADER = "access-token";
    private final MemberRepository memberRepository;
    private final RestTemplateConfig restTemplateConfig;

    @Override
    @Transactional(readOnly = true)
    public User getUserFindById(String id) throws MemberIdNotFoundException {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberIdNotFoundException(id));
    }

    @Override
    @Transactional
    public User save(final String code) {
        return memberRepository.save(
                getTokenInDodam(code).getData().toEntity()
        );
    }

    private DodamInfoResponse getTokenInDodam(final String code) {

        TokenResponse tokenResponse = restTemplateConfig.dodamAuthTemplate()
                .postForObject("/token", new HttpEntity<>(new TokenRequest(code), null), TokenResponse.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN_HEADER, tokenResponse.getData().getToken());
        return restTemplateConfig.dodamOpenTemplate().exchange(
                "/user",
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                DodamInfoResponse.class
        ).getBody();
    }
}
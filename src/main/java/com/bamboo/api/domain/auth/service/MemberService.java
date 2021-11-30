package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.auth.response.MemberWithTokenResponse;
import com.bamboo.api.domain.models.User;

public interface MemberService {

    User getUserFindById(String id);

    MemberWithTokenResponse save(String code);
}
package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.models.User;

public interface MemberService {

  User getUserFindById (String id);

  User save (String code);
}
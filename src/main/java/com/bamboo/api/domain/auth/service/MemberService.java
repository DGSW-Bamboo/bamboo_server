package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.auth.exception.MemberIdNotFoundException;
import com.bamboo.api.domain.auth.infrastructure.repository.MemberRepository;
import com.bamboo.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository MemberRepositoryFindUser;

  public User getUserFindById (String id) throws MemberIdNotFoundException {

    Optional<User> user = MemberRepositoryFindUser.findById(id);

    if (user.isEmpty()) {
      throw new MemberIdNotFoundException(id);
    }

    return user.get();
  }
}

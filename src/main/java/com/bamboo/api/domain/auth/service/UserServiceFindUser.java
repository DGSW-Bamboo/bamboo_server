package com.bamboo.api.domain.auth.service;

import com.bamboo.api.domain.auth.exception.UserIdNotFoundException;
import com.bamboo.api.domain.auth.exception.UserMailNotFoundException;
import com.bamboo.api.domain.auth.infrastructure.repository.UserRepositoryFindUser;
import com.bamboo.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceFindUser {

  private final UserRepositoryFindUser userRepositoryFindUser;

  public User getUserFindByEmail (String email) throws UserMailNotFoundException {

    Optional<User> user = userRepositoryFindUser.findByEmail(email);

    if (user.isEmpty()) {
      throw new UserMailNotFoundException(email);
    }

    return user.get();
  }

  public User getUserFindById (String id) throws UserIdNotFoundException {

    Optional<User> user = userRepositoryFindUser.findById(id);

    if (user.isEmpty()) {
      throw new UserIdNotFoundException(id);
    }

    return user.get();
  }
}

package com.bamboo.api.domain.auth.infrastructure.repository;

import com.bamboo.api.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryFindUser extends JpaRepository<User, Long> {

    Optional<User> findById(String id);
}

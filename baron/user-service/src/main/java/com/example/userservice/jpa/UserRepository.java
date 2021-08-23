package com.example.userservice.jpa;

import com.example.userservice.dto.UserDto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
}

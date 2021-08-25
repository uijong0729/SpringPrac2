package com.example.userservice.jpa;

import com.example.userservice.dto.UserDto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    // findBy + [컬럼명] 규칙으로 쿼리 자동생성
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByEmail(String email);
}

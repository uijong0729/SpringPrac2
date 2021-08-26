package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto findUserByUserId(String userId);
    Iterable<UserEntity> findAllUser();
    Boolean deleteUser(String userId);
    UserDto reviseUser(UserDto userDto);
    UserDto getUserDetailsByEmail(String userName);
}

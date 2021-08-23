package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // userDto를 UserEntity에 맵핑 (필드명이 같으면 된다)
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);
        userDto = mapper.map(userEntity, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto findUserByUserId(String userId) {
        Optional<UserEntity> userEntity = userRepository.findByUserId(userId);
        UserDto userDto;

        if(userEntity.isPresent()){
            userDto = new ModelMapper().map(userEntity.get(), UserDto.class);
            List<ResponseOrder> orders = new ArrayList<>();
            userDto.setOrders(orders);
        }else{
            throw new UsernameNotFoundException("User not found");
        }
//        userEntity.ifPresentOrElse(userEntity1 -> {
//
//        }, () -> {
//
//        });
        return userDto;
    }

    @Override
    public Iterable<UserEntity> findAllUser() {
        return userRepository.findAll();
    }
}

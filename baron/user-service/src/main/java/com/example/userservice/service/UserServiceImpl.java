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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

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
            userDto = mapper.map(userEntity.get(), UserDto.class);
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

    @Override
    public Boolean deleteUser(String userId) {
        Optional<UserEntity> entity = userRepository.findByUserId(userId);
        if (entity.isPresent()) {
            userRepository.delete(entity.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDto reviseUser(UserDto userDto) {
        UserEntity entity = mapper.map(userDto, UserEntity.class);
        userRepository.save(entity);
        return userDto;
    }

    /**
     * ID(email)와 PASSWORD 입력 시 로그인처리되는 부분
     *
     * 이 메소드를 구현하면 /login URL에 email과 password정보를 넘기면 Contorller에 구현하지 않았더라도
     * 인증처리가 수행된다.
     *
     * @see {@link com.example.userservice.security.AuthenticationFilter#attemptAuthentication(HttpServletRequest, HttpServletResponse)}
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> entity = userRepository.findByEmail(email);
        // UserEntity user = entity.orElseGet(UserEntity::new);

        if (!entity.isPresent()) {
            throw new UsernameNotFoundException(email);
        } else {
            UserEntity user = entity.get();
            return new User(
                    user.getEmail(), user.getEncryptedPwd(),
                    true, true, true, true,
                    new ArrayList<>()
            );
        }
    }
}

package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper mapper;
    
    // 서비스 간 통신을 위해
    private Environment env;
    private RestTemplate restTemplate;
    private OrderServiceClient orderServiceClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, 
    		BCryptPasswordEncoder passwordEncoder, 
    		ModelMapper mapper,
    		Environment env,
    		RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.env = env;
        this.restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
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
    public UserDto getUserByUserId(String userId) {
        Optional<UserEntity> userEntity = userRepository.findByUserId(userId);
        UserDto userDto;

        if(userEntity.isPresent()){
            userDto = mapper.map(userEntity.get(), UserDto.class);
            // List<ResponseOrder> orders = new ArrayList<>();

//---------------RestTemplate 방식은 FeignClient로 대체--------------------
//
//            // %s 에는 사용자의 ID가 들어간다. (별도의 구성파일에 지정된 주소)
//            String orderUrl = String.format(env.getProperty("order-service.url"), userId);
//            log.info("[orderurl] %s", orderUrl);
//            
//            // ParameterizedTypeReference의 제네릭은 OrderService의 getOrder()의 반환형을 지정
//            ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null, 
//            		new ParameterizedTypeReference<List<ResponseOrder>>() {});
//            
//            // Response로부터 body부분의 리스트를 가져 옴
//            List<ResponseOrder> orderList = orderListResponse.getBody();
//----------------------------------------------------------------------
            
            
            // FeignClient로 다른 서비스의 HTTP를 호출
            // Error Decoder로 예외 핸들링이 적용된 상태 (로직은 FeignErrorDecoder에서 구현)
            List<ResponseOrder> orderList = orderServiceClient.getOrders(userId);
            

            
            // 다른 서비스에서 API를 조회하고 조회 결과로부터 주문정보를 담는다.
            userDto.setOrders(orderList);
        }else{
            throw new UsernameNotFoundException("User not found");
        }
        
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

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        UserDto userDto;
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userDto = mapper.map(userEntity, UserDto.class);
            return userDto;
        }else {
            throw new UsernameNotFoundException(email);
        }
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

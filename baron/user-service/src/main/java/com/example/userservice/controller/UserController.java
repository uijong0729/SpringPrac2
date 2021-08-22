package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *  http://desktop-c999iqc:8000/user-service/health_check
 */
@RestController
@RequestMapping("/user-service")
@Slf4j
public class UserController {
    private Environment env;
    private Greeting greeting;
    private UserService userService;

    @Autowired
    public UserController(Environment env, Greeting greeting, UserService userService) {
        this.env = env;
        this.greeting = greeting;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String status() {
        log.info("check status");
        return String.format("It's working in user-service and port : %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        // application.yml 파일의 설정정보를 가져 옴
        // return env.getProperty("greeting.msg");
        return greeting.getMsg();
    }

    /**
     *
     * @param user
     * @return 제네릭 지정은 임의사항
     */
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        // dto로 바꾸기
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        // 생성했으면 Response의 상태코드는 200번 OK보다는
        // 201번 CREATED를 반환하는 것이 옳다.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);
    }
}

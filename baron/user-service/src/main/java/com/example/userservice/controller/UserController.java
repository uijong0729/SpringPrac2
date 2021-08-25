package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *  http://desktop-c999iqc:8000/user-service/health_check
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UserController {
    private Environment env;
    private Greeting greeting;
    private UserService userService;
    private ModelMapper mapper;

    @Autowired
    public UserController(Environment env, Greeting greeting, UserService userService, ModelMapper mapper) {
        this.env = env;
        this.greeting = greeting;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/health_check")
    public String status() {
        log.info("check status");
        return String.format("It's working in user-service and port : %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        // application.yml 파일의 설정정보를 가져 옴
        // return env.getProperty("greeting.msg");
        log.debug(request.getRemoteAddr());
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
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        // 생성했으면 Response의 상태코드는 200번 OK보다는
        // 201번 CREATED를 반환하는 것이 옳다.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.findAllUser();
        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(user -> result.add(new ModelMapper().map(user, ResponseUser.class)));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * {userId} : Path Variable : RESTful 방식에서 URI에 정보를 담는 방법
     *
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto user = userService.findUserByUserId(userId);
        ResponseUser responseUser = new ModelMapper().map(user, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> deleteUser(@PathVariable("userId") String userId) {
        Boolean result = userService.deleteUser(userId);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/users")
    public ResponseEntity<ResponseUser> updateUser(@RequestBody RequestUser user) {
        UserDto dto = userService.reviseUser(mapper.map(user, UserDto.class));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapper.map(dto, ResponseUser.class));
    }
}

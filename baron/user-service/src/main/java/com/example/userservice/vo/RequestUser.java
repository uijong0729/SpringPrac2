package com.example.userservice.vo;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 값이 바뀌지 않는다면 record가 유리
 */
@Data
public class RequestUser {
        @Size(min = 2, message = "Email not be less than two characters")
        @NotNull
        @Email
        String email;

        @NotNull
        @Size(min = 2, message = "Name not be less than two characters")
        String name;

        @NotNull
        @Size(min = 8, message = "Password must be equal or grater than 8 characters")
        String pwd;
}

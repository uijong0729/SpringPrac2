package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull
    @Size(min = 2)
    @Email
    private String email;

    @NotNull
    @Size(min = 4)
    private String password;
}

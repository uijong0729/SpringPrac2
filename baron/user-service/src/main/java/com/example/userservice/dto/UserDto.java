package com.example.userservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserDto {
    public String email;
    public String name;
    public String pwd;
    public String userId;
    public Date createdAt;
    public String encryptedPwd;
}
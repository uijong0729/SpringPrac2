package com.example.userservice.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Greeting {
    // org.springframework.beans.factory.annotation.Value;
    @Value("${greeting.msg}")
    private String msg;
}

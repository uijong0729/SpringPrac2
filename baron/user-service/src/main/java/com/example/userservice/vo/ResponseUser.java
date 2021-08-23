package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 사용자에게 반환값을 주기위한 프레임
 * 데이터베이스에서 취득한 데이터를 그대로 사용자에게 전달할 필요가 없기 때문에
 * 필요한 데이터만 필드로 남긴 별도의 클래스를 작성
 * 이때 데이터 변환은 ModelMapper를 이용
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 값 데이터는 버리고, 값이 있는것만 취득
public class ResponseUser {
    private String email;
    private String name;
    private String userId;
    private List<ResponseOrder> orders;
}

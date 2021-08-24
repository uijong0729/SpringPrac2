package com.example.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Null값 데이터는 반환하지 않는다.
public class RequestOrder {
    private String productId;
    private String qty;
    private Integer unitPrice;
}

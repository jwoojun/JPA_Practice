package com.example.jpashop.order.domain.dto;

import com.example.jpashop.order.domain.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}

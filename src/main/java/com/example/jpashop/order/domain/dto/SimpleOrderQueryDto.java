package com.example.jpashop.order.domain.dto;

import com.example.jpashop.order.domain.entity.OrderStatus;
import com.example.jpashop.order.domain.entity.Order;
import com.example.jpashop.member.domain.vo.Address;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SimpleOrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    // DTO는 Entity를 참조해도 괜찮다.
    public SimpleOrderQueryDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }


}

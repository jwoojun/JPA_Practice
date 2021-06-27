package com.example.jpashop.order.domain.dto;

import com.example.jpashop.order.domain.entity.OrderStatus;
import com.example.jpashop.member.domain.vo.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlatDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private String itemNames;
    private int orderPrice;
    private int count;

    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemNames, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemNames = itemNames;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}

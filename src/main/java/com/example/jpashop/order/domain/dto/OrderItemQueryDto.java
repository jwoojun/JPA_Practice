package com.example.jpashop.order.domain.dto;

import lombok.Data;

@Data
public class OrderItemQueryDto {
    private Long orderId;
    private String itemName;
    private int ordeerPrice;
    private int count;

    public OrderItemQueryDto(Long orderId, String itemName, int ordeerPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.ordeerPrice = ordeerPrice;
        this.count = count;
    }
}

package com.example.jpashop.domain;

import com.example.jpashop.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id")
    private Order order;


    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;   // 주문 당시 가격
    private int count;        // 주문 당시 수량

    public OrderItem() {
    }


    // 생성 메서드->실무에서는 훨씬 복잡 -> 수량이 0일 경우
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        return orderItem;
    }
    public void cancle() {
        // 재고수량 복구
        getItem().plusStock(count);

    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();

    }



}

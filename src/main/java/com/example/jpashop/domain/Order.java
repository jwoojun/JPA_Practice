package com.example.jpashop.domain;

import com.example.jpashop.domain.item.Item;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    //양방향
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    // Order itmeA, itemB, itemC 를 하고 em.persis(); -> Cascade는 persist를 전파한다. delete까지.
    public void addMember(@NonNull Member member) {
        this.member = member;
        if (!member.getOrders().contains(member)) {
            member.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    /**
     * 생성 메서드
     **/
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

//        for (OrderItem orderItem : orderItems) {
//            order.addOrderItem(orderItem);
//        }

        Arrays.stream(orderItems)
                .forEachOrdered(order::addOrderItem);

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public void cancle() {
        if (delivery.getStatus() == DeleveryStatus.COMPLETE) {
            throw new IllegalStateException("주문 취소 불가");
        }

        this.setStatus(OrderStatus.CANCLE);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancle();
        }
    }

    // 전체 주문 가격 조회
    public int checkTotalPrice() {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }


}
//양방 원자적으로 해결
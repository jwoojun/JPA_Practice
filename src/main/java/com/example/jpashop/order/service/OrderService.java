package com.example.jpashop.order.service;

import com.example.jpashop.api.dto.OrderSearch;
import com.example.jpashop.delivery.domain.entity.Delivery;
import com.example.jpashop.member.domain.entity.Member;
import com.example.jpashop.order.domain.entity.Order;
import com.example.jpashop.order.domain.entity.OrderItem;
import com.example.jpashop.item.domain.entity.Item;
import com.example.jpashop.item.repository.ItemRepository;
import com.example.jpashop.member.repository.MemberRepository;
import com.example.jpashop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @Transactional
    public Long order(Long memberId, Long itemId, int count) {


        // 회원, 아이템 정보 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // CaseCade는 Order가 관리하는 것.
        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancle();
    }

    public List<Order> findOrders(OrderSearch search) {
        return orderRepository.findByString(search);
    }



}

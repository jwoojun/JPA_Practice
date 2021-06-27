package com.example.jpashop.api;

import com.example.jpashop.order.domain.entity.Order;
import com.example.jpashop.order.domain.entity.OrderItem;
import com.example.jpashop.member.domain.vo.Address;
import com.example.jpashop.order.domain.entity.OrderStatus;
import com.example.jpashop.order.repository.OrderRepository;
import com.example.jpashop.order.domain.dto.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
//    private final OrderQueryRepository queryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> oerdersV1() {
        List<Order> all = orderRepository.findALlByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(o -> o.getItem().getName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findALlByCriteria(new OrderSearch());
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toUnmodifiableList());

    }


    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> collect = orders.stream().map(o -> new OrderDto(o))
                .collect(Collectors.toUnmodifiableList());
        // 1대 다에서는 데이터가 뻥튀기 된다.
        return collect;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "100") int limit
    ) {
//        http://localhost:8080/api/v3.1/orders?offset=0&limit=100
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        // 1대 다에서는 데이터가 뻥튀기 된다.
        return orders.stream().map(OrderDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

//    @GetMapping("/api/v5/orders")
//    public List<OrderFlatDto> ordersV5(){
//        System.out.println("================================1");
//        return queryRepository.findOrders2();
//    }

    @Getter
    @Setter
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
//            order.getOrderItems()
//                    .forEach(o->o.getItem().getName());
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toUnmodifiableList());
        }

    }


    @Getter
    static class OrderItemDto {
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }

    }


}

//package com.example.jpashop.api;
//
//import com.example.jpashop.domain.*;
//import com.example.jpashop.order.domain.dto.OrderQueryDto;
//import com.example.jpashop.order.domain.entity.Order;
//import com.example.jpashop.member.domain.vo.Address;
//import com.example.jpashop.order.repository.OrderRepository;
//import com.example.jpashop.order.domain.dto.OrderSearch;
//import com.example.jpashop.repository.query.OrderQueryRepository;
//import com.example.jpashop.order.service.OrderService;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequiredArgsConstructor
//public class SimpleApiController {
//    // toOne관계 최적화
//    private final OrderService orderService;
//    private final OrderRepository orderRepository;
//    private final OrderQueryRepository orderQueryRepository;
//
//    @GetMapping("/api/v1/simple-orders")
//    public List<Order> ordersv1() {
//        List<Order> all = orderRepository.findALlByCriteria(new OrderSearch());
//        for (Order order : all) {
//            order.getMember().getName();
//            order.getDelivery().getOrder();
//        }
//        return all;
//
//    }
//
//    @GetMapping("/api/v2/simple-orders")
//    public List<SimpleOrderDto> ordersV2() {
//        return orderRepository.findByString(new OrderSearch()).stream()
//                .map(SimpleOrderDto::new)
//                .collect(Collectors.toUnmodifiableList());
//
//    }
//
//    @GetMapping("/api/v3/simple-orders")
//    public List<SimpleOrderDto> ordersV3() {
//        List<Order> orders = orderRepository.findAllWithItem();
//
//        System.out.println("========================================");
//        for(Order order : orders){
//            System.out.println("order ref= "+order+", id= "+order.getId());
//        }
//        System.out.println("========================================");
//
//        return orders.stream()
//                .map(SimpleOrderDto::new)
//                .collect(Collectors.toUnmodifiableList());
//
//    }
//
////    @GetMapping("/api/v4/simple-orders")
////    public List<SimpleOrderDto> ordersV4() {
////    return orderQueryRepository.findOrderQueryDto();
////    }
//
//    @GetMapping("/api/v4/simple-orders")
//    public List<OrderQueryDto> ordersV4() {
//        return orderQueryRepository.findAllBy_optimazation();
//
//    }
//
//
//
//
//    @Data
//    static class SimpleOrderDto {
//        private Long orderId;
//        private String name;
//        private LocalDateTime orderDate;
//        private OrderStatus orderStatus;
//        private Address address;
//
//        public SimpleOrderDto(Order order) {
//            orderId = order.getId();
//            name = order.getMember().getName();
//            orderDate = order.getOrderDate();
//            orderStatus = order.getStatus();
//            address = order.getDelivery().getAddress();
//        }
//
//
//    }
//}

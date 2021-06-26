package com.example.jpashop.repository.query;

import com.example.jpashop.domain.dto.OrderItemQueryDto;
import com.example.jpashop.domain.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDto() {
        System.out.println("========================2");
        List<OrderQueryDto> result = findOrders();
        result.forEach(o -> {
            List<OrderItemQueryDto> items = findOrderItem(o.getOrderId());
            o.setOrderItems(items);
        });
        Optional.ofNullable("User").ifPresent(NullPointerException::new);
        return result;

    }
    public static void main(String args []){
        String a = null;
        Optional.ofNullable(a).orElseThrow(NullPointerException::new);
        Optional.ofNullable(a).ifPresentOrElse(x-> System.out.println(),NullPointerException::new);

    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery("select new java.com.example.jpashop.domain.SimpleOrderQueryDto(o.id,m.name, o.orderDate, o.status, d.address)" +
                "from Order o " +
                "join o.member m " +
                "join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    public List<OrderItemQueryDto> findOrderItem(Long orderId) {
        return em.createQuery("select new jpashop.src.main.java.com.example.jpashop.domain.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                "from OrderItem oi " +
                "join oi.item i " +
                "where oi.order.id= :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDto> findAllBy_optimazation() {
        System.out.println("=========================================");

        List<OrderQueryDto> result = findOrders();
        System.out.println("=========================================");
        List<Long> orderIds = result.stream()
                .map(OrderQueryDto::getOrderId)
                .collect(Collectors.toList());
        System.out.println("=========================================");

        em.createQuery("select oi from OrderItem oi join oi.item i where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
        System.out.println("=========================================");

//        Map<Long, List<OrderItemQueryDto>> collect = orderItems.stream()
//                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
        return result;
    };

}

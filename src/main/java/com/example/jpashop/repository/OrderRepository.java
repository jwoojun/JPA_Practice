package com.example.jpashop.repository;

import com.example.jpashop.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findALlByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);


        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }


        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();

    }

    public List<Order> findByString(OrderSearch search) {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public List<Order> findAllMemberDelivery() {
        return em.createQuery("select o from Order o join fetch o.member join fetch o.delivery", Order.class).getResultList();
    }

    public List<Order> findAllWithItem() {
        return em.createQuery(
                "select distinct o from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery d " +
                        "join fetch o.orderItems oi " +
                        "join fetch oi.item i", Order.class).getResultList();
    }

    public List<Order> pagingOffTheLimit() {
        // toOne으로 가는 것은 쉽다
        return em.createQuery(
                "select distinct o from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery d " +
                        "join fetch o.orderItems oi " +
                        "join fetch oi.item i", Order.class).getResultList();
    }


    public List<Order> paging() {
        return em.createQuery(
                "select distinct o from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery d " +
                        "join fetch o.orderItems oi " +
                        "join fetch oi.item i", Order.class)
                .setFirstResult(1)
                .setMaxResults(100)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return em.createQuery(
                "select distinct o from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery d ", Order.class
        ).setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

//    private List<OrderItemQueryDto> findOrderItems() {
//        return em.createQuery(
//                "select new jpashop/src/main/java/com/example/jpashop/domain/OrderItemQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
//                        "from Order o " +
//                        "join o.member m" +
//                        "join o.delivery d", OrderQueryDto.class)
//                        .getResultList();
//    }


//
//    public List<SimpleOrderQueryDto> findOrderDtos() {
//        String query = "select new jpabook.jpashop.repository"
//        return em.createQuery("select o from Order o join o.member join o.delivery d", SimpleOrderQueryDto.class).getResultList();
//    }

//    public List<Order> findAll(OrderSearch orderSearch) {
//        String jpql = "select o from Order o join o.member m";
//        boolean isFirstCondition = true;
//        if (orderSearch.getOrderStatus() != null) {
//            if (!isFirstCondition) {
//                jpql += " where";
//                isFirstCondition = false;
//            } else {
//                jpql += "and";
//            }
//            jpql += "o.status= :status";
//        }
//
//        if(StringUtils.hasText(orderSearch.getMemberName())){
//            if(isFirstCondition){
//                jpql+=" where";
//                isFirstCondition=false;
//            }else {
//                jpql += " and";
//            }
//            jpql += "m.name like :name";
//        }


//        em.createQuery(jpql, Order.class)

//        return em.createQuery("select o from Order o join o.member m and m.name like :name", Order.class)
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("name", orderSearch.getMemberName())
//                .setMaxResults(1000)
//                .getResultList();
//    }
}
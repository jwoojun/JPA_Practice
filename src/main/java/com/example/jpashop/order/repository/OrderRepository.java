package com.example.jpashop.order.repository;

import com.example.jpashop.api.dto.OrderSearch;
import com.example.jpashop.order.repository.orderqueryrepo.SimpleOrderQueryDto;
import com.example.jpashop.order.domain.entity.Order;
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

  public List<Order> findAll(OrderSearch orderSearch) {
    return em.createQuery("select o from Order o join o.member m", Order.class).getResultList();
  }

  public List<Order> dynamicQuery(OrderSearch orderSearch) {
    /** Null 값일 경우 동적쿼리가 되어 모든 값을 가져온다. */
    return em.createQuery(
            "select o from Order o join o.member m "
                + "where o.status = :status "
                + "and m.name like :name",
            Order.class)
        .setParameter("status", orderSearch.getOrderStatus())
        .setParameter("name", orderSearch.getMemberName())
        .setMaxResults(1000) // 최대 천 건
        .getResultList();
  }

  public List<Order> findByString(OrderSearch search) {
    return em.createQuery("select o from Order o", Order.class).getResultList();
  }


  public List<Order> findAllMemberDelivery() {

    return em.createQuery(
            "select o from Order o join fetch o.member join fetch o.delivery", Order.class)
        .getResultList();
  }

  public List<Order> findAllWithItem() {
    /**
     * return em.createQuery( "select distinct o from Order o " + "join fetch o.member m " + "join
     * fetch o.delivery d " + "join fetch o.orderItems oi " + "join fetch oi.item i", Order.class)
     * .getResultList();
     */
    List<Order> orders = em.createQuery("select o FROM Order o join o.member", Order.class).getResultList();
    System.out.println(orders.size());

    // 주문은 회원/배송 정보를 다 본다는 것을 통해
    return em.createQuery(
            "select o FROM Order o " +
                    "join fetch o.member m " +
                    "join fetch o.delivery d ",
            Order.class)
        .getResultList();
  }

  public List<Order> pagingOffTheLimit() {
    // toOne으로 가는 것은 쉽다
    return em.createQuery(
            "select distinct o from Order o "
                + "join fetch o.member m "
                + "join fetch o.delivery d "
                + "join fetch o.orderItems oi "
                + "join fetch oi.item i",
            Order.class)
        .getResultList();
  }

  public List<Order> paging() {
    return em.createQuery(
            "select distinct o from Order o "
                + "join fetch o.member m "
                + "join fetch o.delivery d "
                + "join fetch o.orderItems oi "
                + "join fetch oi.item i",
            Order.class)
        .setFirstResult(1)
        .setMaxResults(100)
        .getResultList();
  }
  /**
   * Order을 건드리지 않고 가지고 왔다.
   * select절에 들어가는
   * */
  public List<Order> findAllWithMemberDelivery() {
    return em.createQuery("select o from Order o " +
            "join fetch o.member m " +
            "join fetch o.delivery d", Order.class)
            .getResultList();

  }

  /**
   * Repository 재사용성이 떨어진다.
   * API스펙에 맞춰 코드가 Repository에 들어가는 단점이 있다.
   * Repository는 엔티티를 조회하는 용도지 API스펙이 이렇게
   * 들어오면 안된다. Repository를 뜯어내야한다. API에 맞춰.
   * */
  public List<SimpleOrderQueryDto> findOrderDtos() {
    return em.createQuery(
            "select new com.example.jpashop.api.dto.SimpleOrderQueryDto(o.id, m.name, o.orderDate,  o.status, d.address) "
                    + "from Order o "
                    + "join o.member m "
                    + "join o.delivery d ",
            SimpleOrderQueryDto.class)
            .getResultList();
  }



//  public List<SimpleOrderQueryDto> findAllWithMemberDelivery(int offset, int limit) {
//    return em.createQuery(
//            "select distinct o from Order o "
//                + "join fetch o.member m "
//                + "join fetch o.delivery d ",
//            Order.class)
//        .setFirstResult(offset)
//        .setMaxResults(limit)
//        .getResultList();
//  }

  //    private List<OrderItemQueryDto> findOrderItems() {
  //        return em.createQuery(
  //                "select new
  // jpashop/src/main/java/com/example/jpashop/domain/OrderItemQueryDto(o.id, m.name, o.orderDate,
  // o.status, d.address) " +
  //                        "from Order o " +
  //                        "join o.member m" +
  //                        "join o.delivery d", OrderQueryDto.class)
  //                        .getResultList();
  //    }

  //
  //    public List<SimpleOrderQueryDto> findOrderDtos() {
  //        String query = "select new jpabook.jpashop.repository"
  //        return em.createQuery("select o from Order o join o.member join o.delivery d",
  // SimpleOrderQueryDto.class).getResultList();
  //    }

  public List<Order> badDynamicQueryV1(OrderSearch orderSearch) {
    String jpql = "select o from Order o join o.member m";
    boolean isFirstCondition = true;
    if (orderSearch.getOrderStatus() != null) {
      if (!isFirstCondition) {
        jpql += " where";
        isFirstCondition = false;
      } else {
        jpql += "and";
      }
      jpql += "o.status= :status";
    }

    if (StringUtils.hasText(orderSearch.getMemberName())) {
      if (isFirstCondition) {
        jpql += " where";
        isFirstCondition = false;
      } else {
        jpql += " and";
      }
      jpql += "m.name like :name";
    }

    TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(100);

    if (orderSearch.getOrderStatus() != null) {
      query = query.setParameter("status", orderSearch.getOrderStatus());
    }

    if (StringUtils.hasText(orderSearch.getMemberName())) {
      query = query.setParameter("name", orderSearch.getMemberName());
    }
    return query.getResultList();
  }

  /** JPA Criteria */
  public List<Order> badDynamicQueryV2(OrderSearch orderSearch) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Order> cq = cb.createQuery(Order.class);
    Root<Order> o = cq.from(Order.class);
    Join<Object, Object> m = o.join("member", JoinType.INNER);

    List<Predicate> criteria = new ArrayList<>();

    // 주문 상태 검색
    if (orderSearch.getOrderStatus() != null) {
      Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
      criteria.add(status);
    }

    // 회원 이름 검색
    if (StringUtils.hasText(orderSearch.getMemberName())) {
      Predicate name = cb.like(m.get("name"), "%" + orderSearch.getMemberName() + "%");
      criteria.add(name);
    }

    cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
    TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
    return query.getResultList();
  }
}

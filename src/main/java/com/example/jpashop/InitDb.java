//package com.example.jpashop;
//
//import com.example.jpashop.domain.*;
//import com.example.jpashop.domain.entity.item.Book;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit();
//        initService.dbInit();
//
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final EntityManager em;
//
//        public void dbInit() {
//
//            Member member = createMember("memberA", "Seoul", "30", "101");
//            Book bookA = createBook("bookA", 10000, 3);
//            em.persist(bookA);
//
//            Book bookB = createBook("bookB", 12000, 5);
//            em.persist(bookB);
//
//            OrderItem orderItemA = OrderItem.createOrderItem(bookA, 10000, 1);
//            OrderItem orderItemB = OrderItem.createOrderItem(bookB, 20000, 2);
//
//            Delivery delivery = new Delivery();
//            delivery.setAddress(member.getAddress());
//
//            Order order = Order.createOrder(member, delivery, orderItemA, orderItemB);
//            em.persist(order);
//
//        }
//
//        private Member createMember(String name, String city, String streetCode, String zipcode) {
//            Member member = new Member();
//            member.setName(name);
//            member.setAddress(new Address(city, streetCode, zipcode));
//            em.persist(member);
//            return member;
//        }
//
//
//        private Book createBook(String name, int price, int quantity) {
//            Book book = new Book();
//            book.setName(name);
//            book.setPrice(price);
//            book.setStockQuantity(quantity);
//            return book;
//        }
//    }
//}

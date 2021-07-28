package com.example.jpashop.test;

import com.example.jpashop.member.domain.vo.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional @SpringBootTest
class CategoryTest {
    @Autowired CategoryRepository categoryRepository;
    @Autowired StoreRepository storeRepository;
//    @PersistenceContext
//    EntityManager em;
    @Test @Commit
    void test(){
        Category categoryA = Category.createCategory("중식");
        Category categoryB = Category.createCategory("한식");
        Category categoryC = Category.createCategory("김밥");

        categoryRepository.save(categoryA);
        categoryRepository.save(categoryB);
//      em.persist(categoryC);
        Store storeA = Store.createStore(new Address("Seoul", "Mapo", "12341234"), "연우김밥");
        storeRepository.save(storeA);

//        em.flush();
//        em.clear();




    }

}
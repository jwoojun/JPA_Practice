package com.example.jpashop.domain.entity.item;

import com.example.jpashop.JpashopApplication;
import com.example.jpashop.item.domain.entity.Item;
import com.example.jpashop.item.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;


@Transactional
@SpringBootTest
@ContextConfiguration(classes = JpashopApplication.class)
class ItemTest {

    @Autowired
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void text_test() throws Exception{
        // given
        Item item = new Item();
        item.setStockQuantity(3 );
        em.persist(item);

        // when
        Item findItem = itemRepository.findOne(item.getId());


        System.out.println(findItem.getId());
        System.out.println(item.getId());

        // then
        assertEquals(item, findItem);


    }

}
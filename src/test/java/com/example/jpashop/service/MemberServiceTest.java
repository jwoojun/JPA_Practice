package com.example.jpashop.service;

import ch.qos.logback.core.pattern.SpacePadder;
import com.example.jpashop.JpashopApplication;
import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.MemberRepository;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


import static org.junit.jupiter.api.Assertions.*;

@Commit
@Transactional // 모두 롤백.
@SpringBootTest
@ContextConfiguration(classes = JpashopApplication.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void save() throws Exception {
        System.out.println("========");
        Member member = new Member();
        member.setName("Jang");
        em.persist(member);
        em.flush();
        Long saveId = memberService.join(member);
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test
    void 회원가입_예외발생() throws Exception {
        Member memberA = new Member();
        memberA.setName("memberA");
        em.persist(memberA);

        Member memberAA = new Member();
        memberAA.setName("memberA");
        em.persist(memberAA);
//        new AssertionError("예외 발생");
    }

    @Test
    void float_versus() throws Exception {

        // given
        Float numberA = 3F;

        // when
        Float numberB = 4F;

        System.out.println("=============================");
        System.out.println(Float.compare(numberA, numberB));
        System.out.println("=============================");
        Float.compare(numberA, numberB);
        assertEquals(numberA, numberB);
    }


    //    @Override
//    public boolean equals(Object o) {
//        if (o instanceof CaseInsensitiveString) {
//            return str.equalsIgnoreCase(((CaseInsensitiveString) o).str);
//        }
//
//        if (o instanceof String) { //한 방향으로만 작동!!
//            return str.equalsIgnoreCase((String) o);
//        }
//        return false;
//    }

}
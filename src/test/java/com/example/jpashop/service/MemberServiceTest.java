package com.example.jpashop.service;

import com.example.jpashop.JpashopApplication;
import com.example.jpashop.member.domain.entity.Member;
import com.example.jpashop.member.repository.MemberRepository;
import com.example.jpashop.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Commit
@Transactional
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
        // given
        Member member = new Member();
        member.setName("Kang");

        // when
        Long saveId = memberService.join(member);
        em.flush();

        // then
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test
    void duplicate_member_check() throws Exception {
        Member memberA = new Member();
        memberA.setName("memberA");
        memberService.join(memberA);

        Member duplicateMemberA = new Member();
        duplicateMemberA.setName("memberA");
        memberService.join(duplicateMemberA);
    }


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
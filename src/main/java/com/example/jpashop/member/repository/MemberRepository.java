package com.example.jpashop.member.repository;

import com.example.jpashop.member.domain.entity.Member;
import com.zaxxer.hikari.HikariConfig;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.spi.CopyOnWrite;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

  @PersistenceContext private final EntityManager em;
//  @PersistenceContext private final EntityManager em;
//  MemberRepository(EntityManager em){
//    this.em = em;
//  }

  public void svae(Member member) {
    em.persist(member);
  }

  public Member findOne(Long id) {
    return em.find(Member.class, id);
  }

  public List<Member> findAll() {

    return em.createQuery("select m from Member m", Member.class).getResultList();
  }

  public List<Member> findByName(String name) {
    return em.createQuery("select m from Member m where m.name=:name", Member.class)
        .setParameter("name", name)
        .getResultList();
  }
}

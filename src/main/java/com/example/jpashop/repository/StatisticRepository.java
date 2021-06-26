package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticRepository {
    @PersistenceContext //-> Autowired로도 제공된다.
    private final EntityManager em;

    public List<Member> findCity() {
        String jpql = "select m from Member m inner join m.address where m.address.city = 'busan'";
        return em.createQuery(jpql, Member.class).getResultList();
    }
}

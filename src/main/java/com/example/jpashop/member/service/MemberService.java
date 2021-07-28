package com.example.jpashop.member.service;

import com.example.jpashop.member.domain.entity.Member;
import com.example.jpashop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.svae(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        // 멀티 스레드에서 정말 동시에 was가 받으면 동시두명가입 쌉가능그래서 이걸 막아줘야함. 유니크 제약조건으로.
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (findMembers.size() > 0) {
            throw new IllegalStateException("이미  존재하는 회원입니다.");
        }
    }

    private void validateDuplicateMember_V2(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (findMembers.isEmpty()) {
            throw new IllegalStateException("이미  존재하는 회원입니다.");
        }
    }



    @Transactional(readOnly = true)
    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }



    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Modifying
    @Transactional(readOnly = true)
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);

    }
}

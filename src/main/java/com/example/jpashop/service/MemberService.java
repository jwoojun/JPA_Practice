package com.example.jpashop.service;

import com.example.jpashop.domain.entity.Member;
import com.example.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service// Spring annotation
//@AllArgsConstructor
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired 가짜 객체를 끼워넣을 수 있다.
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // @Autowired // mock 객체를 주입할 수도 있다.
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    @Transactional // Spring annotation
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.svae(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        // member의 이름에 unique 제약조건을 걸어주는 것이 좋다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
//        if (!findMembers.isEmpty()){
        if (findMembers.size() > 0) {
            System.out.println("=========================");
            throw new IllegalStateException("이미  존재하는 회원입니다.");
        }
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);

    }
}

package com.example.jpashop.controller;

import com.example.jpashop.domain.entity.Member;
import com.example.jpashop.domain.vo.Address;
import com.example.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/api";
    }


    //추가
    @GetMapping(value = "/members")
    public String list(Model model) {
        System.out.println("====================================");
        List<Member> member20s = memberService.findMembers()
                .stream()
                .filter(Address::isBusan)
                .collect(Collectors.toUnmodifiableList());

        int total = member20s.size();
        long manLivesBusan = member20s.stream()
                .filter(Member::isMan)
                .filter(Address::isBusan)
                .count();


        memberService.findMembers()
                .stream()
                .filter(Address::isBusan)
                .forEach(System.out::println);
        System.out.println("====================================");


        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);
        return "members/memberList";
    }
}
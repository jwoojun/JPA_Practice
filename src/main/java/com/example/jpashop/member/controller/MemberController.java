package com.example.jpashop.member.controller;

import com.example.jpashop.member.domain.dto.MemberForm;
import com.example.jpashop.member.domain.entity.Member;
import com.example.jpashop.member.domain.vo.Address;
import com.example.jpashop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

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

  @GetMapping(value = "/members")
  public String findMembers(Model model) {
    System.out.println("=============");
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}

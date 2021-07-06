package com.example.jpashop.member.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "이름을 반드시 입력해주세요.")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}

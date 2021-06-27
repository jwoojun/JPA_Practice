package com.example.jpashop.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
//    @NotEmpty(message = "이름을 반드시 입력해주세요.")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}

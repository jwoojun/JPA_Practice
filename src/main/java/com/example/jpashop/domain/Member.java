package com.example.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotEmpty
    private String name;
    private String gender;

    private int age;


    // api에는 엔티티를 절대 노출하면 안된. 엔티티에 필드를 반환했다 쳐봐. 그러멵? 필드도 추가됨. 패스워드가 노출되고 . API 스펙이 변한다.
    @Embedded
    private Address address;

    public static boolean isMan(Member member) {
        return "M".equals(member.getGender());
    }

    public static boolean isWoman(Member member) {
        return "F".equals(member.getGender());
    }


    @JsonIgnore
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    public Member() { }

}

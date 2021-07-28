package com.example.jpashop.member.domain.vo;

import com.example.jpashop.member.domain.entity.Member;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Age {
    private int age;



    public int getAge() {
        return age;
    }

    protected Age() {
    }

    public Age(int age) {
        this.age = age;
    }

}
package com.example.jpashop.member.domain.vo;

import com.example.jpashop.member.domain.entity.Member;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Age {
    private int age;

    public boolean between10and20(Member member){
        return member.getAge().getAge()>=10 || member.getAge().getAge()<20;
    }

    public boolean between20and30(Member member) {
        return member.getAge().getAge() >= 20 || member.getAge().getAge() < 30;
    }

    public boolean between30and40(Member member) {
        return member.getAge().getAge() >= 30 || member.getAge().getAge() < 40;
    }

    public boolean between40and50(Member member) {
        return member.getAge().getAge() >= 40 || member.getAge().getAge() < 50;
    }

    public boolean between50and60(Member member) {
        return member.getAge().getAge() >= 50 || member.getAge().getAge() < 60;
    }

    public boolean between60and70(Member member) {
        return member.getAge().getAge() >= 60 || member.getAge().getAge() < 70;
    }

    public boolean over70(Member member) {
        return member.getAge().getAge() >= 70;
    }


    public int getAge() {
        return age;
    }

    protected Age() {
    }

    public Age(int age) {
        this.age = age;
    }

}
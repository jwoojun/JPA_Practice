package com.example.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @Access(AccessType.FIELD)
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    protected Address() {
    }

    public static boolean isBusan(Member member) {
        return "Busan".equals(member.getAddress().getCity());
    }

    public static boolean isSeoul(Member member){
        return "Seoul".equals(member.getAddress().getCity());
    }

    public static boolean isDaeJeon(Member member){
        return "DaeJeon".equals(member.getAddress().getCity());
    }


}

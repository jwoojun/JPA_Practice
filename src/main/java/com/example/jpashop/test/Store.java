package com.example.jpashop.test;


import com.example.jpashop.member.domain.vo.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long id;

    private String name;

    private Address address;

    @OneToMany
    List<CategoryStore> categoryStores = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public List<CategoryStore> getCategoryStores() {
        return categoryStores;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static Store createStore(Address address, String name){
        Store store = new Store();
        store.setAddress(address);
        store.setName(name);
        return store;

    }
}

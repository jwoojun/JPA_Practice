package com.example.jpashop.products.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("ALBUM")
public class Album extends Item {
    private String artise;
    private String etc;
}

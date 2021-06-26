package com.example.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @ToString
@Setter @DiscriminatorValue("BOOK")
public class Book extends Item {
    private String author;
    private String isbn;
}

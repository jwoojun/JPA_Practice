package com.example.jpashop.products.domain.entity;

import com.example.jpashop.categories.domain.entity.Category;
import com.example.jpashop.delivery.domain.entity.NoEnoughStock;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void plusStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void minusStock(int quantity) {
        // 한글이 왔을 때는?
        // 공백이 포함되어 있을 때는?
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NoEnoughStock("수량이 없습니다.");
        }
        this.stockQuantity -= quantity;
    }


}

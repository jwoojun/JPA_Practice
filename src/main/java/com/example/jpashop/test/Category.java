package com.example.jpashop.test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_ID")
  private Long id;

  @OneToOne
  private Category parent;

  public void setCategoryStores(List<CategoryStore> categoryStores) {
    this.categoryStores = categoryStores;
  }

  private String name;

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany
  private List<CategoryStore> categoryStores = new ArrayList<>();

  protected Category() {}
  ;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<CategoryStore> getCategoryStores() {
    return categoryStores;
  }

  public Category(String name) {
    this.name = name;
  }

  public static Category createCategory(String name) {
    Category category = new Category();
    category.name = name;
    return category;
  }
}

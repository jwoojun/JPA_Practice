package com.example.jpashop.test;

import javax.persistence.*;

@Entity
public class CategoryStore {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_STORE_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "STORE_ID")
  private Store store;

  public CategoryStore(Category category, Store store) {
    this.category = category;
    this.store = store;
  }

  protected CategoryStore() {}

  public static CategoryStore createCategoryStore(Category category, Store store) {
    CategoryStore categoryStore = new CategoryStore(category, store);
    System.out.println(
        "=============================================================================================");
    store.getCategoryStores().add(categoryStore);
    System.out.println(
        "=============================================================================================");
    //        category.getCategoryStores().add(categoryStore);
    //    CategoryStore.createCategoryStore(category.getCategory(), store);
    return categoryStore;
  }
}

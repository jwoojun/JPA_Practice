package com.example.jpashop.products.service;

import com.example.jpashop.products.domain.entity.Item;
import com.example.jpashop.products.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
}

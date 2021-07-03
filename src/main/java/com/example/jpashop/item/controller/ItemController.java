package com.example.jpashop.item.controller;

import com.example.jpashop.item.domain.dto.BookForm;
import com.example.jpashop.item.domain.entity.Book;
import com.example.jpashop.item.domain.entity.Item;
import com.example.jpashop.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setPrice(bookForm.getPrice());
        itemService.saveItem(book);
        System.out.println(02);
        return "redirect:/api";
    }


    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItem();
        model.addAttribute("items", items);
        return "items/itemList";
    }


    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {

        Book item = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setName(item.getName());
        form.setIsbn(item.getIsbn());
        form.setId(item.getId());
        form.setPrice(item.getPrice());
        form.setAuthor(item.getAuthor());
        form.setStockQuantity(item.getStockQuantity());
        model.addAttribute("form", form);
        return "items/itemFormUpdate";
    }


    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@RequestBody BookForm form, @PathVariable("itemId") Long itemId) {
//        https://blog.naver.com/writer0713/221853596497
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }

//    @PostMapping(value = "/items/{itemId}/edit")
//    public String updateItemForm2(@ModelAttribute BookForm form, @PathVariable("itemId") Long itemId) {
//        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
//        return "redirect:/items";
//    }

}
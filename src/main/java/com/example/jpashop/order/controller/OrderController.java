package com.example.jpashop.order.controller;

import com.example.jpashop.member.domain.entity.Member;
import com.example.jpashop.order.domain.entity.Order;
import com.example.jpashop.products.domain.entity.Item;
import com.example.jpashop.order.domain.dto.OrderSearch;
import com.example.jpashop.products.service.ItemService;
import com.example.jpashop.member.service.MemberService;
import com.example.jpashop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItem();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/api";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch search, Model model) {
        // 단순 위임만 하는 경우에는 controller에서 Repository로 바로 가는 것도 고려해볼 수 있다.
        List<Order> orders = orderService.findOrders(search);
        model.addAttribute("orders", orders);
        return "order/orderList";

    }

    @PostMapping("/orders/{orderId}/cancle")
    public String cancleOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancleOrder(orderId);
        return "redirect:/orders";
    }
}

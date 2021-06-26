package com.example.jpashop.controller;

import com.example.jpashop.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String home() {
        logger.info("home controller");
        System.out.println("=============================");
        return "home";
    }
    @Autowired
    StatisticService service;


    @GetMapping("/busan")
    public void test(){
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        service.findCIty("busan");
    }

}

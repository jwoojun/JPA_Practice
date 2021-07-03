package com.example.jpashop;

import com.example.jpashop.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JpashopApplication {
@Autowired OrderRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }

//    @Bean
//    Hibernate5Module hibernate5Module(){
//        Hibernate5Module hibernate5Module = new Hibernate5Module();
////        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
//        return hibernate5Module;
//    }
}
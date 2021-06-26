package com.example.jpashop.service;

import com.example.jpashop.domain.vo.Address;
import com.example.jpashop.domain.entity.Member;
import com.example.jpashop.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    @Autowired
    StatisticRepository statisticRepository;

    @Transactional
    public List<Member> findCIty(String city) {
        statisticRepository.findCity().stream()
                .filter(Address::isBusan)
                .collect(Collectors.toList());
        return statisticRepository.findCity();
    }

    @Transactional
    public Map<String, Object> utilization() {
        Map map = new ConcurrentHashMap();

        return map;

    }
}

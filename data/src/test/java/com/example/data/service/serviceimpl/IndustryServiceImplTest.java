package com.example.data.service.serviceimpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndustryServiceImplTest {

    @Autowired
    IndustryServiceImpl service;

    @Test
    void findIndustryByName() {
        List<String> list = new ArrayList<>();
        list.add("交通运输");
        list.add("仪器仪表");
        System.out.println(service.findIndustryByName(list).getContent());
    }
}
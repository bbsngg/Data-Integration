package com.example.data.service.serviceimpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExecutiveServiceImplTest {

    @Autowired
    ExecutiveServiceImpl service;

    @Test
    void getAllName() {
        System.out.println(service.getAllName().getContent());
    }

    @Test
    void findExecutiveByName() {

        List<String> list = new ArrayList<>();
        list.add("王建业");
        list.add("张必书");
        System.out.println(service.findExecutiveByName(list).getContent());
    }
}
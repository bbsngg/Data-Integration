package com.example.data.service.serviceimpl;

import com.example.data.po.Concept;
import com.example.data.service.ConceptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConceptServiceImplTest {

    @Autowired
    ConceptServiceImpl service;

    @Test
    void findConceptByName() {
        List<String> list = new ArrayList<>();
        list.add("股权激励");
        list.add("油气改革");

        System.out.println(service.findConceptByName(list).getContent());
    }

    @Test
    void testFindConceptByName() {

        List<String> list = new ArrayList<>();
        list.add("3D打印");
        list.add("4G概念");
        System.out.println(service.findConceptByName(list).getContent());
    }
}
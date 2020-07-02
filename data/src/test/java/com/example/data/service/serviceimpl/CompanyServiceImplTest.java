package com.example.data.service.serviceimpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CompanyServiceImplTest {

    @Autowired
    CompanyServiceImpl service;

    @Test
    void findCompanyByStockCode() {
        List<Long> codes = new ArrayList<>();
        codes.add(Long.parseLong("600082"));
        codes.add(Long.parseLong("601616"));
        service.findCompanyByStockCode(codes);
    }

    @Test
    void getAllStockCode() {
        System.out.println(service.getAllStockCode().getContent());
    }
}
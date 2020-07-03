package com.example.data.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyRepositoryTest {

    @Autowired
    CompanyRepository repository;

    @Test
    void findByIndustryId() {
        System.out.println(repository.findByIndustryId(Long.parseLong("100000032")));
    }
}
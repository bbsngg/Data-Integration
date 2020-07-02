package com.example.data.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.example.data.po.Executive;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExecutiveRepositoryTest {

  @Autowired
  ExecutiveRepository executiveRepository;
  @Test
  void findByStockCode() {
    List<Executive> res = executiveRepository.findByStockCode(1L);
    assertTrue(res.size()>0);
  }
}
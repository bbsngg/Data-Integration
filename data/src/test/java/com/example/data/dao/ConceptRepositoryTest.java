package com.example.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.data.po.Concept;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConceptRepositoryTest {
  @Autowired
  ConceptRepository conceptRepository;
  @Test
  void findByStockCode() {
    List<Concept> res = conceptRepository.findByStockCode(1L);
    assertEquals(8,res.size());
  }

    @Test
    void getAllName() {
    }

    @Test
    void findByName() {
      List<Concept> res = conceptRepository.findByName("股权激励");
      System.out.println(res);
    }
}
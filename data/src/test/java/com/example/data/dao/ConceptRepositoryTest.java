package com.example.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.data.po.Concept;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class ConceptRepositoryTest {
  @Autowired
  ConceptRepository conceptRepository;
  @Test
  void findByStockCode() {
    List<Concept> res = conceptRepository.findByStockCode(1L);
    assertEquals(8,res.size());
  }
}
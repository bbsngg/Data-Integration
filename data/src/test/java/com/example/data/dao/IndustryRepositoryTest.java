package com.example.data.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.example.data.po.Industry;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IndustryRepositoryTest {

  @Autowired
  IndustryRepository industryRepository;

  @Test
  void findByIndustryId() {
    List<Industry> res = industryRepository.findByIndustryId(100000000L);
    assertEquals(1,res.size());
  }

  @Test
  void findByName() {
    System.out.println(industryRepository.findByName("仪器仪表"));
  }
}
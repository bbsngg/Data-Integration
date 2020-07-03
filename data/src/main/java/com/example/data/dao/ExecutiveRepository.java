package com.example.data.dao;

import com.example.data.po.Concept;
import com.example.data.po.Executive;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, Long> {

  /**
   * 根据股票代码查询executive
   * @param stockCode 股票代码
   * @return executive
   */
  List<Executive> findByStockCode(Long stockCode);

  @Query("select new java.lang.String(i.name) from Executive i ")
  List<String> getAllName();

  //@Query("select c from Executive c where c.name =?1")
  List<Executive> findByName(String name);
}

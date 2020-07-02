package com.example.data.dao;

import com.example.data.po.Concept;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Long> {

  /**
   * 根据stockcode查询concept
   *
   * @param stockCode 股票代码
   * @return concept
   */
  @Query("select c from Concept c where c.id in (select sc.conceptId from StockConcept sc where sc.stockCode = ?1)")
  List<Concept> findByStockCode(Long stockCode);

  @Query("select new java.lang.String(i.name) from Concept i ")
  List<String> getAllName();

  List<Concept> findByName(String name);

}

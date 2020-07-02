package com.example.data.dao;

import com.example.data.po.Industry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry,Long> {

  /**
   * 根据id寻找industry
   * @param id id
   * @return industry
   */
  @Query("select i from Industry i where i.id = ?1")
  List<Industry> findByIndustryId(Long id);
}

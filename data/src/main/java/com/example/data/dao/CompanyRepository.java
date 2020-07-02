package com.example.data.dao;

import com.example.data.po.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  @Query("select c from Company c where c.id < 3000")
  List<Company> findTopTenCompany();

  Company findByStockCode(Long code);

}

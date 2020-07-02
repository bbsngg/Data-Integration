package com.example.data.service.serviceimpl;

import com.example.data.dao.CompanyRepository;
import com.example.data.po.Company;
import com.example.data.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
  final CompanyRepository companyRepository;

  @Autowired
  public TestServiceImpl(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Override
  public List<Company> findSomeCompany() {
    List<Company> list = companyRepository.findTopTenCompany();

    return list;
  }
}

package com.example.data.service.serviceimpl;

import com.example.data.dao.CompanyRepository;
import com.example.data.dao.ConceptRepository;
import com.example.data.dao.ExecutiveRepository;
import com.example.data.dao.IndustryRepository;
import com.example.data.po.Company;
import com.example.data.po.Concept;
import com.example.data.po.Executive;
import com.example.data.po.Industry;
import com.example.data.service.CompanyService;
import com.example.data.service.IndustryService;
import com.example.data.vo.CompanyVO;
import com.example.data.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndustryServiceImpl implements IndustryService {
  final IndustryRepository industryRepository;

  @Autowired
  public IndustryServiceImpl(IndustryRepository industryRepository) {
    this.industryRepository = industryRepository;
  }

  @Override
  public ResponseVO getAllName() {

    try{
      return ResponseVO.buildSuccess(industryRepository.getAllName());
    }
    catch (Exception e){
      System.out.println("获取所有产业的名字失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }
}

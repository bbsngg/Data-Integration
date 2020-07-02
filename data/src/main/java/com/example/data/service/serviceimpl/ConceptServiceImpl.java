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
import com.example.data.service.ConceptService;
import com.example.data.vo.CompanyVO;
import com.example.data.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConceptServiceImpl implements ConceptService {
  final ConceptRepository conceptRepository;

  @Autowired
  public ConceptServiceImpl(ConceptRepository conceptRepository) {
    this.conceptRepository = conceptRepository;
  }

  @Override
  public ResponseVO getAllId() {

    try{
      List<Concept> list = conceptRepository.findAll();
      List<Long> ret = new ArrayList<>();
      for (Concept temp: list){
        ret.add(temp.getId());
      }
      return ResponseVO.buildSuccess(ret);
    }
    catch (Exception e){
      System.out.println("获取所有概念的代号失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }
}

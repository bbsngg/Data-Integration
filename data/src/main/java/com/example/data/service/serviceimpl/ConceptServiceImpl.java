package com.example.data.service.serviceimpl;

import com.example.data.dao.CompanyRepository;
import com.example.data.dao.ConceptRepository;
import com.example.data.po.*;
import com.example.data.service.ConceptService;
import com.example.data.vo.ConceptVO;
import com.example.data.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConceptServiceImpl implements ConceptService {
  final ConceptRepository conceptRepository;
  final CompanyRepository companyRepository;

  @Autowired
  public ConceptServiceImpl(ConceptRepository conceptRepository, CompanyRepository companyRepository) {
    this.conceptRepository = conceptRepository;
    this.companyRepository = companyRepository;
  }

  @Override
  public ResponseVO getAllName() {

    try{
      return ResponseVO.buildSuccess(conceptRepository.getAllName());
    }
    catch (Exception e){
      System.out.println("获取所有概念的名字失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }

  @Override
  public ResponseVO findConceptByName(List<String> list) {
    try{
      List<ConceptVO> ret = new ArrayList<>();
      for (String name : list){
        List<Concept> temp = conceptRepository.findByName(name);
        if (temp.size()==0)
          continue;
        Concept concept = temp.get(0);
        ConceptVO vo = new ConceptVO();
        vo.setConcept(concept);

        Long conceptId = concept.getId();
        vo.setCompanies(companyRepository.findByConceptId(conceptId));

        ret.add(vo);
      }
      return ResponseVO.buildSuccess(ret);
    }
    catch (Exception e){
      System.out.println("根据概念名获取多个概念的相关信息失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }


}

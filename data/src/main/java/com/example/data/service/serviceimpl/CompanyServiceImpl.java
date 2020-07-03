package com.example.data.service.serviceimpl;

import com.example.data.dao.CompanyRepository;
import com.example.data.dao.ConceptRepository;
import com.example.data.dao.ExecutiveRepository;
import com.example.data.dao.IndustryRepository;
import com.example.data.po.*;
import com.example.data.service.CompanyService;
import com.example.data.service.TestService;
import com.example.data.vo.CompanyVO;
import com.example.data.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
  final CompanyRepository companyRepository;
  final ExecutiveRepository executiveRepository;
  final IndustryRepository industryRepository;
  final ConceptRepository conceptRepository;

  @Autowired
  public CompanyServiceImpl(CompanyRepository companyRepository, ExecutiveRepository executiveRepository, IndustryRepository industryRepository,ConceptRepository conceptRepository) {
    this.companyRepository = companyRepository;
    this.executiveRepository = executiveRepository;
    this.industryRepository = industryRepository;
    this.conceptRepository = conceptRepository;
  }

  /**
   * 通过stockcode获取股票信息，包括基本信息，股东信息，概念信息，行业信息
   * @param code_list
   * @return List<Company>
   */
  @Override
  public ResponseVO findCompanyByStockCode(List<Long> code_list) {
    try{
      List<CompanyVO> ret = new ArrayList<>();
      for (Long code : code_list){
        Company company = companyRepository.findByStockCode(code);
        CompanyVO vo = new CompanyVO();
        vo.setCompany(company);

        // 获取该股票的股东信息
        List<Executive> executives = executiveRepository.findByStockCode(code);
        vo.setExecutives(executives);

        // 获取该股票的概念信息
        List<Concept> concepts = conceptRepository.findByStockCode(code);
        vo.setConcepts(concepts);

        // 获取该股票的行业信息
        Long industry_id = company.getIndustryId();
        Industry industry = industryRepository.findByIndustryId(industry_id).get(0);
        vo.setIndustry(industry);

        // 加入
        ret.add(vo);
      }
      return ResponseVO.buildSuccess(ret);
    }
    catch (Exception e){
      System.out.println("通过stockcode获取股票信息失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }

  /**
   * 获取所有股票的代号
   * @return List<Long>
   */
  @Override
  public ResponseVO getAllStockCode() {
    try{
      return ResponseVO.buildSuccess(companyRepository.findAllStockCode());
    }
    catch (Exception e){
      System.out.println("获取所有股票的代号失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }
}

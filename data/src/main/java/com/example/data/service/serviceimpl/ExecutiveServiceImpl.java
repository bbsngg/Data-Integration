package com.example.data.service.serviceimpl;

import com.example.data.dao.CompanyRepository;
import com.example.data.dao.ExecutiveRepository;
import com.example.data.po.Company;
import com.example.data.po.Executive;
import com.example.data.service.ExecutiveService;
import com.example.data.vo.ExecutiveVO;
import com.example.data.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {
  final ExecutiveRepository executiveRepository;
  final CompanyRepository companyRepository;

  @Autowired
  public ExecutiveServiceImpl(ExecutiveRepository executiveRepository, CompanyRepository companyRepository) {
    this.executiveRepository = executiveRepository;
    this.companyRepository = companyRepository;
  }

  @Override
  public ResponseVO getAllName() {

    try{
      return ResponseVO.buildSuccess(executiveRepository.getAllName());
    }
    catch (Exception e){
      System.out.println("获取所有董事的名字失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }

  @Override
  public ResponseVO findExecutiveByName(List<String> list) {
    try{
      List<ExecutiveVO> ret = new ArrayList<>();
      for (String name : list){
        List<Executive> temp = executiveRepository.findByName(name);
        if (temp.size()==0)
          continue;
        for (Executive e : temp){
          ExecutiveVO vo = new ExecutiveVO();
          vo.setExecutive(e);

          Long code = e.getStockCode();
          List<Company> companies = new ArrayList<>();
          companies.add(companyRepository.findByStockCode(code));
          vo.setCompanies(companies);

          ret.add(vo);
        }
      }
      return ResponseVO.buildSuccess(ret);
    }
    catch (Exception e){
      System.out.println("根据董事名获取多个董事的相关信息失败");
      return ResponseVO.buildFailure(e.getMessage());
    }
  }


}

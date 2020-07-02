package com.example.data.service;

import com.example.data.po.Company;
import com.example.data.vo.ResponseVO;

import java.util.List;

public interface CompanyService {

  ResponseVO findCompanyByStockCode(List<Long> code_list);

  ResponseVO getAllStockCode();


}

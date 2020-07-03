package com.example.data.controller;

import com.example.data.service.CompanyService;
import com.example.data.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

  final CompanyService service;

  @Autowired
  public CompanyController(CompanyService service) {
    this.service = service;
  }

  @ApiOperation("获取所有股票代码")
  @GetMapping("/getAllStockCode")
  public ResponseVO getAllStockCode(){
    return service.getAllStockCode();
  }

  @ApiOperation("获取多只股票相关信息")
  @PostMapping("/findCompanyByStockCode")
  public ResponseVO findCompanyByStockCode(@RequestBody List<Long> codeList){
    return service.findCompanyByStockCode(codeList);
  }

}

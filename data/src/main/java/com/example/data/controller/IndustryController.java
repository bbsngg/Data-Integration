package com.example.data.controller;

import com.example.data.po.Industry;
import com.example.data.service.CompanyService;
import com.example.data.service.IndustryService;
import com.example.data.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
public class IndustryController {

  final IndustryService service;

  @Autowired
  public IndustryController(IndustryService service) {
    this.service = service;
  }

  @ApiOperation("获取所有股票名字")
  @GetMapping("/getAllName")
  public ResponseVO getAllName(){
    return service.getAllName();
  }

  @ApiOperation("获取多个产业的相关信息，根据产业名")
  @PostMapping("/findIndustryByName")
  public ResponseVO findIndustryByName(@RequestBody List<String> list){
    return service.findIndustryByName(list);
  }
}

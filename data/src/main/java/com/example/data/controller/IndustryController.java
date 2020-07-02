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
}

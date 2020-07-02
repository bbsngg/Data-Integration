package com.example.data.controller;

import com.example.data.po.Concept;
import com.example.data.service.CompanyService;
import com.example.data.service.ConceptService;
import com.example.data.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concept")
public class ConceptController {

  final ConceptService service;

  @Autowired
  public ConceptController(ConceptService service) {
    this.service = service;
  }

  @ApiOperation("获取所有概念代码")
  @GetMapping("/getAllId")
  public ResponseVO getAllId(){
    return service.getAllId();
  }

}

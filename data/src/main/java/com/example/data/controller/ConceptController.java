package com.example.data.controller;

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

  @ApiOperation("获取所有概念名字")
  @GetMapping("/getAllName")
  public ResponseVO getAllName(){
    return service.getAllName();
  }


  @ApiOperation("获取多个概念的相关信息，根据概念名")
  @PostMapping("/findConceptByName")
  public ResponseVO findConceptByName(@RequestBody List<String> list){
    return service.findConceptByName(list);
  }

}

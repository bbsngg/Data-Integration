package com.example.data.controller;

import com.example.data.service.ConceptService;
import com.example.data.service.ExecutiveService;
import com.example.data.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/executive")
public class ExecutiveController {

  final ExecutiveService service;

  @Autowired
  public ExecutiveController(ExecutiveService service) {
    this.service = service;
  }

  @ApiOperation("获取所有董事名字")
  @GetMapping("/getAllName")
  public ResponseVO getAllName(){
    return service.getAllName();
  }


  @ApiOperation("获取多个董事的相关信息，根据董事名")
  @PostMapping("/findExecutiveByName")
  public ResponseVO findExecutiveByName(@RequestBody List<String> list){
    return service.findExecutiveByName(list);
  }

}

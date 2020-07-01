package com.example.data.controller;

import com.example.data.po.Company;
import com.example.data.service.TestService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
  final TestService testService;

  @Autowired
  public TestController(TestService testService) {
    this.testService = testService;
  }

  @ApiOperation("测试接口")
  @GetMapping("/get")
  public List<Company> findCompany(){
    return testService.findSomeCompany();
  }
}

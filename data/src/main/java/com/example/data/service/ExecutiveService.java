package com.example.data.service;

import com.example.data.vo.ResponseVO;

import java.util.List;

public interface ExecutiveService {

  ResponseVO getAllName();

  ResponseVO findExecutiveByName(List<String> list);


}

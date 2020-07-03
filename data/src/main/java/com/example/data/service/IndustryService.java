package com.example.data.service;

import com.example.data.vo.ResponseVO;

import java.util.List;

public interface IndustryService {

  ResponseVO getAllName();

  ResponseVO findIndustryByName(List<String> list);

}

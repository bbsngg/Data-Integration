package com.example.data.service;

import com.example.data.vo.ResponseVO;

import java.util.List;

public interface ConceptService {

  ResponseVO getAllName();

  ResponseVO findConceptByName(List<String> list);
}

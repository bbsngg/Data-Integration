package com.example.data.vo;

import com.example.data.po.Company;
import com.example.data.po.Concept;
import com.example.data.po.Executive;

import java.util.List;

public class ExecutiveVO {


    Executive executive;

    List<Company> companies;

    public Executive getExecutive() {
        return executive;
    }

    public void setExecutive(Executive executive) {
        this.executive = executive;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}

package com.example.data.vo;

import com.example.data.po.Company;
import com.example.data.po.Concept;
import com.example.data.po.Industry;

import java.util.List;

public class IndustryVO {


    Industry industry;

    List<Company> companies;

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}

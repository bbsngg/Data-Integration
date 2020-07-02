package com.example.data.vo;

import com.example.data.po.Company;
import com.example.data.po.Concept;
import com.example.data.po.Executive;
import com.example.data.po.Industry;

import java.util.List;

public class ConceptVO {


    Concept concept;

    List<Company> companies;

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}

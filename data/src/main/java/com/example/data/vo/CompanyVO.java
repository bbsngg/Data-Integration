package com.example.data.vo;

import com.example.data.po.Company;
import com.example.data.po.Concept;
import com.example.data.po.Executive;
import com.example.data.po.Industry;

import java.util.List;

public class CompanyVO {


    Company company;

    List<Executive> executives;

    List<Concept> concepts;

    Industry industry;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Executive> getExecutives() {
        return executives;
    }

    public void setExecutives(List<Executive> executives) {
        this.executives = executives;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }
}

package com.relesi.cloudarchitecture.api.dtos;



public class CompanyDto {

    private Long id;
    private String businessName;
    private String ein;

    public CompanyDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", ein='" + ein + '\'' +
                '}';
    }
}

package com.relesi.cloudarchitecture.api.dtos;

public class LegalPersonRegisterDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String ssn;
    private String businessName;
    private String ein;

    private LegalPersonRegisterDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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
        return "LegalPersonRegisterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ssn='" + ssn + '\'' +
                ", businessName='" + businessName + '\'' +
                ", ein='" + ein + '\'' +
                '}';
    }
}

package com.relesi.cloudarchitecture.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty(message = "Name cannot be empty.")
    @Length(min = 3, max = 200, message = "Name must contain between 3 and 200 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Email cannot be empty.")
    @Length(min = 5, max = 200, message = "Email must contain between 5 and 200 characters.")
    @Email(message="Invalid email.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "Password cannot be empty.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "SSN cannot be empty.")
    @CPF(message="Invalid SSN")
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @NotEmpty(message = "Business Name cannot be empty.")
    @Length(min = 5, max = 200, message = "Business Name must contain between 5 and 200 characters.")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @NotEmpty(message = "EIN cannot be empty.")
    @CNPJ(message="Invalid EIN.")
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

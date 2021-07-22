package com.relesi.cloudarchitecture.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class NaturalPersonRegisterDto {


    private Long id;
    private String name;
    private String email;
    private String password;
    private String ssn;
    private String ein;

    private Optional<String> hourValue = Optional.empty();
    private Optional<String> qtyHoursWorkedDay = Optional.empty();
    private Optional<String> qtyLunchHours = Optional.empty();

    public NaturalPersonRegisterDto(){

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

    @NotEmpty(message = "EIN cannot be empty.")
    @CNPJ(message="Invalid EIN.")
    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public Optional<String> getHourValue() {
        return hourValue;
    }

    public void setHourValue(Optional<String> hourValue) {
        this.hourValue = hourValue;
    }

    public Optional<String> getQtyHoursWorkedDay() {
        return qtyHoursWorkedDay;
    }

    public void setQtyHoursWorkedDay(Optional<String> qtyHoursWorkedDay) {
        this.qtyHoursWorkedDay = qtyHoursWorkedDay;
    }

    public Optional<String> getQtyLunchHours() {
        return qtyLunchHours;
    }

    public void setQtyLunchHours(Optional<String> qtyLunchHours) {
        this.qtyLunchHours = qtyLunchHours;
    }

    @Override
    public String toString() {
        return "NaturalPersonRegisterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ssn='" + ssn + '\'' +
                ", ein='" + ein + '\'' +
                ", hourValue=" + hourValue +
                ", qtyHoursWorkedDay=" + qtyHoursWorkedDay +
                ", qtyLunchHours=" + qtyLunchHours +
                '}';
    }
}

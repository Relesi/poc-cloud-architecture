package com.relesi.cloudarchitecture.api.dtos;

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

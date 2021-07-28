package com.relesi.cloudarchitecture.api.dtos;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class EmployeeDto {

    private Long id;
    private String name;
    private String email;

    private Optional<String> password = Optional.empty();
    private Optional<String> hourValue = Optional.empty();
    private Optional<String> qtyHoursWorkedDay = Optional.empty();
    private Optional<String> qtyHoursLunch = Optional.empty();

    public EmployeeDto() {

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
    @Email(message = "Invalid email.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
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

    public Optional<String> getQtyHoursLunch() {
        return qtyHoursLunch;
    }

    public void setQtyHoursLunch(Optional<String> qtyHoursLunch) {
        this.qtyHoursLunch = qtyHoursLunch;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", hourValue=" + hourValue +
                ", qtyHoursWorkedDay=" + qtyHoursWorkedDay +
                ", qtyHoursLunch=" + qtyHoursLunch +
                '}';
    }
}





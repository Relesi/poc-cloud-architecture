package com.relesi.cloudarchitecture.api.dtos;


import com.relesi.cloudarchitecture.api.entities.Launched;

import java.util.Optional;

public class LaunchedDto {


    private Optional<Long> id = Optional.empty();
    private String data;
    private String type;
    private String description;
    private String localization;
    private Long employeeId;

    public LaunchedDto(){

    }

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "LaunchedDto{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", localization='" + localization + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}

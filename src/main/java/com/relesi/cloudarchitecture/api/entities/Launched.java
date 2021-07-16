package com.relesi.cloudarchitecture.api.entities;

import com.relesi.cloudarchitecture.api.enums.TypeEnum;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "launched")
public class Launched implements Serializable {

    private static final long serialVersionUID = 6524560251526772839L;

    private Long id;
    private Date currentDate;
    private String description;
    private String localization;
    private Date creationDate;
    private Date updateDate;
    private TypeEnum type;
    private Employee employee;

    public Launched(){

    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "current_dates", insertable = false, updatable = false)
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "localization", nullable = true)
    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "update_date", nullable = false)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @PreUpdate
    public void PreUpdate() {
        updateDate = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date actual = new Date();
        creationDate = actual;
        updateDate = actual;
    }

    @Override
    public String toString() {
        return "Launched{" +
                "id=" + id +
                ", currentDate=" + currentDate +
                ", description='" + description + '\'' +
                ", localization='" + localization + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", type=" + type +
                ", employee=" + employee +
                '}';
    }
}




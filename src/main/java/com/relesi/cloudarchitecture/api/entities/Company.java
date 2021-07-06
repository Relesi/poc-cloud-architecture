package com.relesi.cloudarchitecture.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = -350928975939694877L;

    private Long id;
    private String businessName;
    private String ein;
    private Date creationDate;
    private Date updateDate;
    private List<Employee> employees;

    public Company() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "business_name", nullable = false)
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Column(name = "ein", nullable = false)
    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
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

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
        return "Company{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", ein='" + ein + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", employees=" + employees +
                '}';
    }
}

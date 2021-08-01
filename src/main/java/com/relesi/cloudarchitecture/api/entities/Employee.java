package com.relesi.cloudarchitecture.api.entities;

import com.relesi.cloudarchitecture.api.enums.ProfileEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 6520651970145214586L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String ssn;
    private BigDecimal hourValue;
    private Float qtyHoursWorkedDay;
    private Float qtyHoursLunch;
    private ProfileEnum profile;
    private Date creationDate;
    private Date updateDate;
    private Company company;
    private List<Launched> launched;

    public Employee() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ssn", nullable = false)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Column(name = "hour_value", nullable = true)
    public BigDecimal getHourValue() {
        return hourValue;
    }

    @Transient
    public Optional<BigDecimal> getHourValueOpt() {
        return Optional.ofNullable(hourValue);
    }

    public void setHourValue(BigDecimal hourValue) {
        this.hourValue = hourValue;
    }

    @Column(name = "qty_hours_worked_day", nullable = true)
    public Float getQtyHoursWorkedDay() {
        return qtyHoursWorkedDay;
    }

    @Transient
    public Optional<Float> getQtyHoursWorkedDayOpt() {
        return Optional.ofNullable(qtyHoursWorkedDay);
    }

    public void setQtyHoursWorkedDay(Float qtyHoursWorkedDay) {
        this.qtyHoursWorkedDay = qtyHoursWorkedDay;
    }

    @Column(name = "qty_hours_launch", nullable = true)
    public Float getQtyHoursLunch() {
        return qtyHoursLunch;
    }

    @Transient
    public Optional<Float> getQtyHoursLunchOpt() {
        return Optional.ofNullable(qtyHoursLunch);
    }

    public void setQtyHoursLunch(Float qtyHoursLunch) {
        this.qtyHoursLunch = qtyHoursLunch;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "profile", nullable = false)
    public ProfileEnum getProfile() {
        return profile;
    }

    public void setProfile(ProfileEnum profile) {
        this.profile = profile;
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

    @ManyToOne(fetch = FetchType.EAGER)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Launched> getLaunched() {
        return launched;
    }

    public void setLaunched(List<Launched> launched) {
        this.launched = launched;
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
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ssn='" + ssn + '\'' +
                ", hourValue=" + hourValue +
                ", qtyHoursWorkedDay=" + qtyHoursWorkedDay +
                ", qtyHoursLunch=" + qtyHoursLunch +
                ", profile=" + profile +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", company=" + company +
                ", launched=" + launched +
                '}';
    }
}

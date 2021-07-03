package com.relesi.cloudarchitecture.api.entities;

import com.relesi.cloudarchitecture.api.enums.ProfileEnum;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Serializable{

	private static final long serialVersionUID = 6520651970145214586L;

	private Long id;
	private String name;
	private String email;
	private String password;
	private String ssn;
	private BigDecimal hourValue;
	private Float qtyHoursWorkedDay;
	private Float qtyLunchHours;
	private ProfileEnum profile;
	private Date creationDate;
	private Date updateDate;
	private Company company;
	private List<Launched> launcheds;

	public Employee(){

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

	public BigDecimal getHourValue() {
		return hourValue;
	}

	public void setHourValue(BigDecimal hourValue) {
		this.hourValue = hourValue;
	}

	public Float getQtyHoursWorkedDay() {
		return qtyHoursWorkedDay;
	}

	public void setQtyHoursWorkedDay(Float qtyHoursWorkedDay) {
		this.qtyHoursWorkedDay = qtyHoursWorkedDay;
	}

	public Float getQtyLunchHours() {
		return qtyLunchHours;
	}

	public void setQtyLunchHours(Float qtyLunchHours) {
		this.qtyLunchHours = qtyLunchHours;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Launched> getLauncheds() {
		return launcheds;
	}

	public void setLauncheds(List<Launched> launcheds) {
		this.launcheds = launcheds;
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
				", qtyLunchHours=" + qtyLunchHours +
				", profile=" + profile +
				", creationDate=" + creationDate +
				", updateDate=" + updateDate +
				", company=" + company +
				", launcheds=" + launcheds +
				'}';
	}
}




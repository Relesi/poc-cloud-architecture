package com.relesi.cloudarchitecture.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable{

	private static final long serialVersionUID = -350928975939694877L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String businessName;
	private String EIN;
	private Date creationDate;
	private Date updateDate;
	private List<Empployee> employees;
	
	
	public Company() {
		
		
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getEIN() {
		return EIN;
	}


	public void setEIN(String eIN) {
		EIN = eIN;
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


	public List<Empployee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Empployee> employees) {
		this.employees = employees;
	}
	
	
	

}

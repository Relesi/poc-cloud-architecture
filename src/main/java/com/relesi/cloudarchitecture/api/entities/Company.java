package com.relesi.cloudarchitecture.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable{

	private static final long serialVersionUID = -350928975939694877L;

	
	private Long id;
	private String businessName;
	private String EIN;
	private Date creationDate;
	private Date updateDate;
	private List<Empployee> employees;
	
	
	public Company() {
		
	}
	

}

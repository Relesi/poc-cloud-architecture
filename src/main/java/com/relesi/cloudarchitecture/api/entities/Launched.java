package com.relesi.cloudarchitecture.api.entities;

import com.relesi.cloudarchitecture.api.enums.TypeEnum;

import java.io.Serializable;
import java.util.Date;

public class Launched implements Serializable {

    private static final long serialVersionUID = 6520651970145214586L;

    private Long id;
    private Date data;
    private String description;
    private String localization;
    private Date creationDate;
    private Date updateDate;
    private TypeEnum type;
    private Employee employee;

    public Launched(){

    }


}

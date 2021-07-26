package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.dtos.CompanyDto;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyContreller {

    private static final Logger log = LoggerFactory.getLogger(CompanyContreller.class);

    @Autowired
    private CompanyService companyService;

    public CompanyContreller(){

    }
    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<Response<CompanyDto>> searchByEin(@PathVariable("ein") String ein){
        log.info("Search Company by EIN: {}", ein);



        return null;

    }








}

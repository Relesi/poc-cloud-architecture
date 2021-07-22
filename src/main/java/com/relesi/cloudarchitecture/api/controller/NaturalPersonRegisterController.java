package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.dtos.NaturalPersonRegisterDto;
import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.relesi.cloudarchitecture.api.services.CompanyService;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/natural-person")
@CrossOrigin(origins = "*")
public class NaturalPersonRegisterController {

    private static final Logger log = LoggerFactory.getLogger(NaturalPersonRegisterController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    public NaturalPersonRegisterController(){

    }

    public ResponseEntity<Response<NaturalPersonRegisterDto>> register(@Valid @RequestBody NaturalPersonRegisterDto naturalPersonRegisterDto,
                                                                       BindingResult result) throws NoSuchAlgorithmException{

        validateExistingData(naturalPersonRegisterDto, result);
        Employee employee = this.convertDtoToEmployee(naturalPersonRegisterDto);


        //TODO
        return null;
    }

    private void validateExistingData(NaturalPersonRegisterDto naturalPersonRegisterDto, BindingResult result) {

    }

    private Employee convertDtoToEmployee(NaturalPersonRegisterDto naturalPersonRegisterDto) {

        //TODO
        return null;

    }



}

package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.dtos.LegalPersonRegisterDto;
import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
import com.relesi.cloudarchitecture.api.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.relesi.cloudarchitecture.api.services.CompanyService;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/legal-person")
@CrossOrigin(origins = "*")
public class LegalPersonRegisterController {

    private static final Logger log = LoggerFactory.getLogger(LegalPersonRegisterController.class);


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    public LegalPersonRegisterController(){

    }

    @PostMapping
    public ResponseEntity<Response<LegalPersonRegisterDto>> register(@Valid @RequestBody LegalPersonRegisterDto legalPersonRegisterDto,
                                                                     BindingResult result) throws NoSuchAlgorithmException{

        log.info("Signing up LP: {}", legalPersonRegisterDto.toString());
        Response<LegalPersonRegisterDto> response = new Response<LegalPersonRegisterDto>();

        validateExistingData(legalPersonRegisterDto, result);
        Company company = this.convertDtoToCompany(legalPersonRegisterDto);
        Employee employee = this.convertDtoToEmployee(legalPersonRegisterDto, result);





        //TODO

        return null;

    }

    /***
     * Checks if the company or employee already exists in the database.
     *
     * @param legalPersonRegisterDto
     * @param result
     */
    private void validateExistingData(LegalPersonRegisterDto legalPersonRegisterDto, BindingResult result) {
        this.companyService.searchByEin(legalPersonRegisterDto.getEin())
                .ifPresent(comp -> result.addError(new ObjectError("company","Existing Company.")));

        this.employeeService.searchBySsn(legalPersonRegisterDto.getSsn())
                .ifPresent(employ -> result.addError(new ObjectError("employee", "Existing SSN")));

        this.employeeService.searchByEmail(legalPersonRegisterDto.getEmail())
                .ifPresent(employ -> result.addError(new ObjectError("employee", "Existing Email")));
    }

    /***
     * Converts DTO data to company.
     *
     * @param legalPersonRegisterDto
     * @return
     */
    private Company convertDtoToCompany(LegalPersonRegisterDto legalPersonRegisterDto) {
        Company company = new Company();
        company.setEin(legalPersonRegisterDto.getEin());
        company.setBusinessName(legalPersonRegisterDto.getBusinessName());

        return  company;
    }

    /***
     * Converts DTO data to employee.
     *
     * @param legalPersonRegisterDto
     * @param result
     * @return Employee
     * @throws NoSuchAlgorithmException
     */
    private Employee convertDtoToEmployee(LegalPersonRegisterDto legalPersonRegisterDto, BindingResult result) throws NoSuchAlgorithmException {
        Employee employee = new Employee();
        employee.setName(legalPersonRegisterDto.getName());
        employee.setEmail(legalPersonRegisterDto.getEmail());
        employee.setSsn(legalPersonRegisterDto.getSsn());
        employee.setProfile(ProfileEnum.ROLE_ADMIN);
        employee.setPassword(PasswordUtils.generateBCrypt(legalPersonRegisterDto.getPassword()));

        return employee;
    }

}

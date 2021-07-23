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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<Response<NaturalPersonRegisterDto>> register(@Valid @RequestBody NaturalPersonRegisterDto naturalPersonRegisterDto,
                                                                       BindingResult result) throws NoSuchAlgorithmException{
        log.info("Registering NP: {}", naturalPersonRegisterDto.toString());
        Response<NaturalPersonRegisterDto> response = new Response<NaturalPersonRegisterDto>();

        validateExistingData(naturalPersonRegisterDto, result);
        Employee employee = this.convertDtoToEmployee(naturalPersonRegisterDto);

        if (result.hasErrors()) {
            log.error("Error validating NP registration data: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Company> company = this.companyService.searchByEin(naturalPersonRegisterDto.getEin());
        company.ifPresent(comp -> employee.setCompany(comp));
        this.employeeService.persist(employee);

        response.setData(this.convertNaturalPersonRegisterDto(employee));
        return ResponseEntity.ok(response);
    }



    private void validateExistingData(NaturalPersonRegisterDto naturalPersonRegisterDto, BindingResult result) {


    }

    private Employee convertDtoToEmployee(NaturalPersonRegisterDto naturalPersonRegisterDto) {

        //TODO
        return null;

    }

    private NaturalPersonRegisterDto convertNaturalPersonRegisterDto(Employee employee) {

        //TODO
        return null;
    }



}

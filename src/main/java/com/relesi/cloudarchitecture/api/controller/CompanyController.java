package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.dtos.CompanyDto;
import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    public CompanyController() {

    }

    /***
     * Return a company given a EIN
     *
     * @param ein
     * @return
     */
    @GetMapping(value = "/ein/{ein}")
    public ResponseEntity<Response<CompanyDto>> searchByEin(@PathVariable("ein") String ein) {
        log.info("Search Company by EIN: {}", ein);

        Response<CompanyDto> response = new Response<CompanyDto>();
        Optional<Company> company = companyService.searchByEin(ein);

        if (!company.isPresent()) {
            log.info("Company not found for the EIN: {}", ein);
            response.getErrors().add("Company not found for the EIN " + ein);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.convertCompanyDto(company.get()));
        return ResponseEntity.ok(response);

    }

    /***
     * Populates a DTO with a company's data
     *
     * @param company
     * @return
     */
    private CompanyDto convertCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setEin(company.getEin());
        companyDto.setBusinessName(company.getBusinessName());

        return companyDto;
    }

}

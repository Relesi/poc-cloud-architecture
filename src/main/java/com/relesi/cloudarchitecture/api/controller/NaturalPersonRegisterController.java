package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.dtos.NaturalPersonRegisterDto;
import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
import com.relesi.cloudarchitecture.api.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.relesi.cloudarchitecture.api.services.CompanyService;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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

    /***
     * Register a employee natural person in the system.
     *
     * @param naturalPersonRegisterDto
     * @param result
     * @return ResponseEntity<Response<NaturalPersonRegisterDto>
     * @throws NoSuchAlgorithmException
     */
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


    /***
     * Check if the company is registered and if the employee does not exist in the database.
     *
     * @param naturalPersonRegisterDto
     * @param result
     */
    private void validateExistingData(NaturalPersonRegisterDto naturalPersonRegisterDto, BindingResult result) {
        Optional<Company> company = this.companyService.searchByEin(naturalPersonRegisterDto.getEin());
        if (!company.isPresent()) {
            result.addError(new ObjectError("company", "Company not registered."));
        }

        this.employeeService.searchBySsn(naturalPersonRegisterDto.getSsn())
                .ifPresent(employ -> result.addError(new ObjectError("employee", "Existing SSN.")));
        this.employeeService.searchByEmail(naturalPersonRegisterDto.getEmail())
                .ifPresent(employ -> result.addError(new ObjectError("employee", "Existing Email.")));
    }


    /***
     * Converts DTO data to employee.
     *
     * @param naturalPersonRegisterDto
     * @return
     * @throws NoSuchAlgorithmException
     */
    private Employee convertDtoToEmployee(NaturalPersonRegisterDto naturalPersonRegisterDto) throws NoSuchAlgorithmException {

        Employee employee = new Employee();

        employee.setName(naturalPersonRegisterDto.getName());
        employee.setEmail(naturalPersonRegisterDto.getEmail());
        employee.setSsn(naturalPersonRegisterDto.getSsn());
        employee.setProfile(ProfileEnum.ROLE_USUARIO);
        employee.setPassword(PasswordUtils.generateBCrypt(naturalPersonRegisterDto.getPassword()));
        naturalPersonRegisterDto.getQtyLunchHours().ifPresent(qtyLunchHours -> employee.setQtyHoursLunch(Float.valueOf(qtyLunchHours)));
        naturalPersonRegisterDto.getQtyHoursWorkedDay().ifPresent(qtyHoursWorkedDay -> employee.setQtyHoursWorkedDay(Float.valueOf(qtyHoursWorkedDay)));
        naturalPersonRegisterDto.getHourValue().ifPresent(hourValue -> employee.setHourValue(new BigDecimal(hourValue)));

        return employee;

    }

    private NaturalPersonRegisterDto convertNaturalPersonRegisterDto(Employee employee) {

        //TODO
        return null;
    }



}

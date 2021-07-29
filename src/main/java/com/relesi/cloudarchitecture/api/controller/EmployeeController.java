package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.dtos.EmployeeDto;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import com.relesi.cloudarchitecture.api.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(){

    }

    /***
     * Updates Data of an employee
     *
     * @param id
     * @param employeeDto
     * @param result
     * @return ResponseEntity<Response<EmployeeDto>>
     * @throws NoSuchAlgorithmException
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<EmployeeDto>> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody EmployeeDto employeeDto, BindingResult result) throws NoSuchAlgorithmException{

        log.info("Employee Update: {}", employeeDto.toString());
        Response<EmployeeDto> response = new Response<EmployeeDto>();

        Optional<Employee> employee = this.employeeService.searchById(id);
        if (!employee.isPresent()) {
            result.addError(new ObjectError("employee", "Employee not found."));
        }

        this.updateEmployeeData(employee.get(), employeeDto, result);

        if (result.hasErrors()) {
            log.error("Error validating employee: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.employeeService.persist(employee.get());
        response.setData(this.convertEmployeeToDto(employee.get()));

        return ResponseEntity.ok(response);
    }

    /***
     * Updates employee data based on data found in the DTO.
     *
     * @param employee
     * @param employeeDto
     * @param result
     * @throws NoSuchAlgorithmException
     */
    private void updateEmployeeData(Employee employee, EmployeeDto employeeDto, BindingResult result) throws NoSuchAlgorithmException {

        employee.setName(employeeDto.getName());

        if(!employee.getEmail().equals(employeeDto.getEmail())){
            this.employeeService.searchByEmail(employeeDto.getEmail())
                    .ifPresent(empl -> result.addError(new ObjectError("emial", "Existing Email")));
            employee.setEmail(employeeDto.getEmail());
        }

        employee.setQtyHoursLunch(null);
        employeeDto.getQtyHoursLunch()
                .ifPresent(qtyHoursLunch -> employee.setQtyHoursLunch(Float.valueOf(qtyHoursLunch)));

        employee.setQtyHoursWorkedDay(null);
        employeeDto.getQtyHoursWorkedDay()
                .ifPresent(qtyHoursWorkedDay -> employee.setQtyHoursWorkedDay(Float.valueOf(qtyHoursWorkedDay)));

        employee.setHourValue(null);
        employeeDto.getHourValue().ifPresent(hourValue -> employee.setHourValue(new BigDecimal(hourValue)));

        if (employeeDto.getPassword().isPresent()) {
            employee.setPassword(PasswordUtils.generateBCrypt(employeeDto.getPassword().get()));
        }

    }

    private EmployeeDto convertEmployeeToDto(Employee employee) {

        return null;
    }


    




}

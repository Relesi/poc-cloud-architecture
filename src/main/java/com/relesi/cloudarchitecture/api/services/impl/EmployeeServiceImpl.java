package com.relesi.cloudarchitecture.api.services.impl;

import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.repositories.EmployeeRepository;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee persist(Employee employee) {
        log.info("Persisting Employee: {}", employee);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> searchBySsn(String ssn) {
        log.info("Looking for a employee for the SSN {}", ssn);
        return Optional.ofNullable(employeeRepository.findBySsn(ssn));

    }

    @Override
    public Optional<Employee> searchByEmail(String email) {
        log.info("Looking for a employee for the Email {}", email);
        return Optional.ofNullable(employeeRepository.findByEmail(email));
    }

    @Override
    public Optional<Employee> searchById(Long id) {
        log.info("Looking for a employee for the ID {}", id);
        return this.employeeRepository.findById(id);
    }
}

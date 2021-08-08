package com.relesi.cloudarchitecture.api.services;

import com.relesi.cloudarchitecture.api.entities.Employee;

import java.util.Optional;

public interface EmployeeService {

    /**
     * An employee remains in the database.
     *
     * @param employee
     * @return Employee
     */

    Employee persist(Employee employee);

    /**
     * Search and return an employee given a SSN.
     *
     * @param ssn
     * @return Optional<Employee>
     */

    Optional<Employee> searchBySsn(String ssn);

    /**
     * Search and return an employee given a SSN.
     *
     * @param email
     * @return Optional<Employee>
     */

    Optional<Employee> searchByEmail(String email);

    /**
     * Search and return an employee by ID.
     *
     * @param id
     * @return Optional<Employee>
     */

    Optional<Employee> searchById(Long id);
}

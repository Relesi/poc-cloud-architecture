package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findBySsn(String Ssn);
    Employee findByEmail(String email);
    Employee findBySsnOrEmail(String ssn, String email );

}

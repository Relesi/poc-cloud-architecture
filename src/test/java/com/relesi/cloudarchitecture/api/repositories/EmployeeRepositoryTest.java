package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private static final String EMAIL = "email@email.com";
    private static final String SSN = "00851463070";

    @Before
    public void setUp() throws Exception{
        Company company = this.companyRepository.save(getCompanyData());
        this.employeeRepository.save(getEmployeeData(company));
    }

    @After
    public final void tearDown(){
        this.companyRepository.deleteAll();
    }

    public void testSearchByEmail(){
        Employee employee = this.employeeRepository.findByEmail(EMAIL);
        assertEquals(EMAIL, employee.getEmail());
    }

    @Test
    public void testSearchByEin(){
        Employee employee = this.employeeRepository.findBySsn(SSN);
        assertEquals(SSN, employee.getSsn());
    }


}

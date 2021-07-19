package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
import com.relesi.cloudarchitecture.api.utils.PasswordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void setUp() throws Exception {
        Company company = this.companyRepository.save(getCompanyData());
        this.employeeRepository.save(getEmployeeData(company));
    }

    @After
    public final void tearDown() {
        this.companyRepository.deleteAll();
    }

    @Test
    public void testSearchByEmail() {
        Employee employee = this.employeeRepository.findByEmail(EMAIL);
        assertEquals(EMAIL, employee.getEmail());
    }

    @Test
    public void testSearchByEin() {
        Employee employee = this.employeeRepository.findBySsn(SSN);
        assertEquals(SSN, employee.getSsn());
    }

    @Test
    public void testSearchEmployeeByEmailOrSsnToEmailInvalid() {
        Employee employee = this.employeeRepository.findBySsnOrEmail(SSN, "email@invalido.com");
        assertNotNull(employee);
    }

    @Test
    public void testSearchEmployeeByEmailAndSsnToEmailInvalid() {
        Employee employee = this.employeeRepository.findBySsnOrEmail("123456789", EMAIL);
        assertNotNull(employee);
    }

    private Employee getEmployeeData(Company company) throws NoSuchAlgorithmException {
        Employee employee = new Employee();
        employee.setName("Renato Lessa");
        employee.setProfile(ProfileEnum.ROLE_USUARIO);
        employee.setPassword(PasswordUtils.generateBCrypt("123456"));
        employee.setSsn(SSN);
        employee.setEmail(EMAIL);
        employee.setCompany(company);
        return employee;
    }

    private Company getCompanyData() {
        Company company = new Company();
        company.setBusinessName("Company Technology");
        company.setEin("78349766000173");
        return company;
    }


}

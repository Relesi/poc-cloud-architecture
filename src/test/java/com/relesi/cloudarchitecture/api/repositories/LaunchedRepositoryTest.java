package com.relesi.cloudarchitecture.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
import com.relesi.cloudarchitecture.api.enums.TypeEnum;
import com.relesi.cloudarchitecture.api.utils.PasswordUtils;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LaunchedRepositoryTest {

    @Autowired
    private LaunchedRepository launchedRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Long employeeId;

//    @Before
//    public void setUp() throws Exception {
//        final Company company = this.companyRepository.save(getCompanyData());
//
//        final Employee employee = this.employeeRepository.save(getEmployeeData(company));
//        this.employeeId = employee.getId();
//
//        this.launchedRepository.save(getLaunchedData(employee));
//        this.launchedRepository.save(getLaunchedData(employee));
//    }

    @After
    public void tearDown() throws Exception {
        this.companyRepository.deleteAll();
    }

    @Test
    public void testSearchLaunchedByEmployeeIdPaged(){
        PageRequest page = new PageRequest(0, 10);
        Page<Launched> launcheds = this.launchedRepository.findByEmployeeId(employeeId, page);
        assertEquals(0, launcheds.getTotalElements());
    }

    @Test
    public void testSearchLaunchedByEmployeeId(){
        final List<Launched> launcheds = this.launchedRepository.findByEmployeeId(employeeId);
        assertEquals(0, launcheds.size());
    }

    private Launched getLaunchedData(Employee employee) {
        final Launched launched = new Launched();
        launched.setCreationDate(new Date());
        launched.setType(TypeEnum.START_LUNCH);
        launched.setEmployee(employee);
        return launched;
    }

    private Employee getEmployeeData(Company company) throws NoSuchAlgorithmException {
        final Employee employee = new Employee();
        employee.setName("Renato Lessa");
        employee.setProfile(ProfileEnum.ROLE_USUARIO);
        employee.setPassword(PasswordUtils.generateBCrypt("123456"));
        employee.setSsn("04105663062");
        employee.setEmail("email@email.com");
        employee.setCompany(company);
        return employee;
    }

    private Company getCompanyData() {
        final Company company = new Company();
        company.setBusinessName("Company Technology");
        company.setEin("78349766000173");
        return company;
    }

}

package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
import com.relesi.cloudarchitecture.api.enums.TypeEnum;
import com.relesi.cloudarchitecture.api.utils.PasswordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Before
    public void setUp() throws Exception {
        Company company = this.companyRepository.save(getCompanyData());

        Employee employee = this.employeeRepository.save(getEmployeeData(company));
        this.employeeId = employee.getId();

        this.launchedRepository.save(getLaunchedData(employee));
        this.launchedRepository.save(getLaunchedData(employee));
    }

//    @Test
//    public void testSearchLaunchedByEmployeeId(){
//        PageRequest page = new PageRequest(0, 10);
//        List<Launched> launcheds = this.launchedRepository.findByEmployeeId(employeeId);
//        //assertEquals(2, launcheds.getClass());
//    }

    @Test
    public void testSearchLaunchedByEmployeeIdPaged(){
        List<Launched> launcheds = this.launchedRepository.findByEmployeeId(employeeId);
        assertEquals(2, launcheds.size());
    }

    private Launched getLaunchedData(Employee employee) {
        Launched launched = new Launched();
        launched.setCreationDate(new Date());
        launched.setType(TypeEnum.START_LUNCH);
        launched.setEmployee(employee);
        return launched;
    }

    private Employee getEmployeeData(Company company) throws NoSuchAlgorithmException {
        Employee employee = new Employee();
        employee.setName("Renato Lessa");
        employee.setProfile(ProfileEnum.ROLE_USUARIO);
        employee.setPassword(PasswordUtils.generateBCrypt("123456"));
        employee.setSsn("04105663062");
        employee.setEmail("email@email.com");
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

package com.relesi.cloudarchitecture.api.controller;



import com.relesi.cloudarchitecture.api.dtos.EmployeeDto;
import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.services.CompanyService;
import com.relesi.cloudarchitecture.api.services.EmployeeService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LegalPersonControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private CompanyService companyService;

    private static final String URL_BASE = "/api/legal-person/";
    private static final Long ID_EMPLOYEE = 3L;
    private static final String NAME = "Renato-Lessa";
    private static final String EMAIL = "admin@relesi.com.br";
    private static final String PASSWORD = "123456";
    private static final String BUSINESSNAME = "Relesi-Thinking-IT";
    private static final String SSN = "37807791020";
    private static final String EIN = "68326664000189";

    private static final Long ID_COMPANY =1L;

    private static final BigDecimal HOURVALUE = new BigDecimal(1);
    private static final Float QTYHOURSWORKEDDAY = 9.0f;
    private static final Float QTYHOURSLUNCH = 2.0f;
    private static final String PROFILE = ProfileEnum.ROLE_ADMIN.name();

    private static final Date DATA = new Date();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Ignore
    @Test
    @WithMockUser
    public void testRegisterLegalPerson() throws Exception {
        Employee employee = getEmployeeData();
        BDDMockito.given(this.employeeService.persist(Mockito.any(Employee.class))).willReturn(employee);
        Company company = getCompanyData();
        BDDMockito.given(this.companyService.persist(Mockito.any(Company.class))).willReturn(company);

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                        .content(this.getJsonRequisitionPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.date.id").value(ID_EMPLOYEE))
                .andExpect(jsonPath("$.date.name").value(NAME))
                .andExpect(jsonPath("$.date.email").value(EMAIL))
                .andExpect(jsonPath("$.date.password").value(PASSWORD))
                .andExpect(jsonPath("$.date.ssn").value(SSN))
                .andExpect(jsonPath("$.date.businessName").value(BUSINESSNAME))
                .andExpect(jsonPath("$.date.ein").value(EIN))
                .andExpect(jsonPath("$.errors").isEmpty());


    }

    private Company getCompanyData() {

        return null;
    }

    private String getJsonRequisitionPost() throws JsonProcessingException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(null);
        employeeDto.setName("renato");
        employeeDto.setEmail("renato@renato.com");
        employeeDto.setPassword(Optional.empty());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(employeeDto);
    }

    private Employee getEmployeeData() {

        Employee employee = new Employee();
        employee.setId(ID_EMPLOYEE);
        employee.setName(NAME);
        employee.setEmail(EMAIL);
        employee.setPassword(PASSWORD);
        employee.setSsn(SSN);

        employee.setHourValue(HOURVALUE);
        employee.setQtyHoursWorkedDay(QTYHOURSWORKEDDAY);
        employee.setQtyHoursLunch(QTYHOURSLUNCH);
        employee.setProfile(ProfileEnum.valueOf(PROFILE));

        employee.setCompany(new Company());
        employee.getCompany().setId(ID_COMPANY);
        employee.getCompany().setBusinessName(BUSINESSNAME);
        employee.getCompany().setEin(EIN);

        employee.getCompany().setCreationDate((DATA));
        employee.getCompany().setUpdateDate(DATA);
        employee.setLaunched(new ArrayList<Launched>());



        return employee;

    }
}

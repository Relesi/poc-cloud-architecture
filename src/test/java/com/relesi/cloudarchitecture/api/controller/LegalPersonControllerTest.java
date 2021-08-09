package com.relesi.cloudarchitecture.api.controller;


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

    private static final String URL_BASE = "/api/legal-person";
    private static final Long ID_EMPLOYEE = 3L;
    private static final String NAME = "Renato-Lessa";
    private static final String EMAIL = "admin@relesi.com.br";
    private static final String PASSWORD = "123456";
    private static final String BUSINESSNAME = "Relesi-Thinking-IT";
    private static final String SSN = "37807791020";


    @Test
    @WithMockUser
    public void testRegisterLegalPerson() throws Exception{
        Employee employee = getEmployeeData();
        BDDMockito.given(this.employeeService.persist(Mockito.any(Employee.class))).willReturn(employee);

    }

    private Employee getEmployeeData() {

        Employee employee = new Employee();
        employee.setId(ID_EMPLOYEE);
        employee.setName(NAME);
        employee.setEmail(EMAIL);
        employee.setPassword(PASSWORD);
        employee.setSsn(SSN);
        return employee;

    }
}

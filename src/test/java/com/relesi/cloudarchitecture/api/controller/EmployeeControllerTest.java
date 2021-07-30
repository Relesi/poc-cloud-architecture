package com.relesi.cloudarchitecture.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relesi.cloudarchitecture.api.dtos.EmployeeDto;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    private static final String UPDATE_EMPLOYEE_BY_ID = "/api/employee/id/";
    private static final Long ID = Long.valueOf(1);
    private static final String NAME = "RENATO_LESSA";
    private static final String EMAIL = "nicolas@relesi.com.br";


    @Test
    @WithMockUser
    public void testUpdateEmployeeById() throws Exception {
        Employee employee = getDataEmployee();
        BDDMockito.given(this.employeeService.persist(Mockito.any(Employee.class))).willReturn(employee);

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_EMPLOYEE_BY_ID)
                .content(this.getJsonRequisitionPut())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.email").value(EMAIL))



        ))

    }

    private String getJsonRequisitionPut() throws JsonProcessingException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(null);
        employeeDto.setName(NAME);
        employeeDto.setEmail(EMAIL);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(employeeDto);

    }

    private Employee getDataEmployee() {
        Employee employee = new Employee();
        employee.setId(ID);
        employee.setName(NAME);
        employee.setEmail(EMAIL);

        return employee;

    }


}

package com.relesi.cloudarchitecture.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    private static final String UPDATE_EMPLOYEE = "/api/employee/";
    private static final String UPDATE_EMPLOYEE1 = "/api/employee/ssn";
    private static final Long ID = 3L;
    private static final String NAME = "Nicolas Lessa";
    private static final String EMAIL = "nicolas@relesi.com.br";
    private static final String PASSWORD = "123";
    private static final String SSN = "37324621023";

    private static final BigDecimal HOURVALUE = new BigDecimal(1);
    private static final Float QTYHOURSWORKEDDAY = 9.0f;
    private static final Float QTYHOURSLUNCH = 2.0f;
    private static final String PROFILE = ProfileEnum.ROLE_ADMIN.name();


    private static final Date DATA = new Date();


    private static final Company BUSINESS_NAME = new Company();

    private static final List<Launched> LAUNCHED = Collections.singletonList(new Launched());

    //@Test(expected = NestedServletException.class)
    @Test
    @WithMockUser
    public void testUpdateEmployeeById() throws Exception {
        Employee employee = getDataEmployee();

        BDDMockito.given(this.employeeService.persist(Mockito.any(Employee.class))).willReturn(employee);

        mvc.perform(MockMvcRequestBuilders.post(UPDATE_EMPLOYEE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));


    }


    @Test
    @WithMockUser
    public void testSearchEmployeeSsnInvalid() throws Exception {
        BDDMockito.given(this.employeeService.searchBySsn(Mockito.anyString())).willReturn(Optional.of(this.getDataEmployee()));

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_EMPLOYEE + SSN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser
    public void testEmployeeSearchBySsnValid() throws Exception {
        BDDMockito.given(this.employeeService.searchBySsn(Mockito.anyString())).willReturn(Optional.of(this.getDataEmployee()));

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_EMPLOYEE1 + SSN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


    private Employee getDataEmployee() {
        Employee employee = new Employee();
        employee.setId(ID);
        employee.setName(NAME);
        employee.setEmail(EMAIL);
        employee.setPassword(PASSWORD);
        employee.setSsn(SSN);
        employee.setHourValue(HOURVALUE);
        employee.setQtyHoursWorkedDay(QTYHOURSWORKEDDAY);
        employee.setQtyHoursLunch(QTYHOURSLUNCH);
        employee.setProfile(ProfileEnum.valueOf(PROFILE));
        employee.setCreationDate(DATA);
        employee.setUpdateDate(DATA);
        employee.setCompany(BUSINESS_NAME);
        employee.setLaunched(LAUNCHED);

        return employee;

    }


}

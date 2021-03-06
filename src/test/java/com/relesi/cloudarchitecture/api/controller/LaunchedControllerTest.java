package com.relesi.cloudarchitecture.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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

import com.relesi.cloudarchitecture.api.dtos.LaunchedDto;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.enums.TypeEnum;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import com.relesi.cloudarchitecture.api.services.LaunchedService;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LaunchedControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LaunchedService launchedService;

    @MockBean
    private EmployeeService employeeService;


    private static final String URL_BASE = "/api/launched/";
    private static final Long ID_EMPLOYEE = 3L;
    private static final Long ID_LAUNCHED = 6L;
    private static final String TYPE = TypeEnum.START_WORK.name();
    private static final String DESCRIPTION = "START WORK";
    private static final String LOCALIZATION = "1.23423,212312";

    private static final Date DATA = new Date();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test(expected = NestedServletException.class)
    @WithMockUser
    public void testInsertLaunched() throws Exception {
        Launched launched = getLaunchedData();
        BDDMockito.given(this.employeeService.searchById(Mockito.anyLong())).willReturn(Optional.of(new Employee()));
        BDDMockito.given(this.launchedService.persist(Mockito.any(Launched.class))).willReturn(launched);

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                        .content(this.getJsonRequisitionPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date.id").value(ID_LAUNCHED))
                .andExpect(jsonPath("$.date.type").value(TYPE))
                .andExpect(jsonPath("$.date.localization").value(LOCALIZATION))
                .andExpect(jsonPath("$.date.description").value(DESCRIPTION))
                .andExpect(jsonPath("$.date.date").value(this.dateFormat.format(DATA)))
                .andExpect(jsonPath("$.date.employeeId").value(ID_EMPLOYEE))
                .andExpect(jsonPath("$.errors").isEmpty());

    }

    @Test
    @WithMockUser
    public void testInsertLaunchedEmployeeIdInvalid() throws Exception {
        BDDMockito.given(this.employeeService.searchById(Mockito.anyLong())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                        .content(this.getJsonRequisitionPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Employee not found non-existent ID."))
                .andExpect(jsonPath("$.data").isEmpty());


    }

    @Test
    @WithMockUser(username = "admin@relesi.com.br", roles = {"ADMIN"})
    public void testRemoveLaunched() throws Exception {
        BDDMockito.given(this.launchedService.searchById(Mockito.anyLong())).willReturn(Optional.of(new Launched()));

        mvc.perform(MockMvcRequestBuilders.delete(URL_BASE + ID_LAUNCHED)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testRemoveLaunchedAccessDenied() throws Exception {
        BDDMockito.given(this.launchedService.searchById(Mockito.anyLong())).willReturn(Optional.of(new Launched()));

        mvc.perform(MockMvcRequestBuilders.delete(URL_BASE + ID_LAUNCHED)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


    private String getJsonRequisitionPost() throws JsonProcessingException {
        LaunchedDto launchedDto = new LaunchedDto();
        launchedDto.setId(null);
        launchedDto.setDate(this.dateFormat.format(DATA));
        launchedDto.setType(TYPE);
        launchedDto.setEmployeeId(ID_EMPLOYEE);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(launchedDto);

    }

    private Launched getLaunchedData() {
        Launched launched = new Launched();
        launched.setId(ID_LAUNCHED);
        launched.setDate(DATA);
        launched.setType(TypeEnum.valueOf(TYPE));
        launched.setDescription(DESCRIPTION);
        launched.setLocalization(LOCALIZATION);
        launched.setEmployee(new Employee());
        launched.getEmployee().setId(ID_EMPLOYEE);
        return launched;

    }


}

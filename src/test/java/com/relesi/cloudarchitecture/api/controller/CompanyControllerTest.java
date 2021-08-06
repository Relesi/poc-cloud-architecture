//package com.relesi.cloudarchitecture.api.controller;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.BDDMockito;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.relesi.cloudarchitecture.api.entities.Company;
//import com.relesi.cloudarchitecture.api.services.CompanyService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class CompanyControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CompanyService companyService;
//
//    private static final String SEARCH_COMPANY_EIN_URL = "/api/company/ein/";
//    private static final Long ID = Long.valueOf(1);
//    private static final String EIN = "36691081000160";
//    private static final String BUSINESS_NAME = "COMPANY_RLS_IT";
//
//    @Test
//    @WithMockUser
//    public void testSearchCompanyEinInvalid() throws Exception{
//        BDDMockito.given(this.companyService.searchByEin(Mockito.anyString())).willReturn(Optional.empty());
//
//        mvc.perform(MockMvcRequestBuilders.get(SEARCH_COMPANY_EIN_URL + EIN).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.errors").value("Company not found for the EIN " + EIN));
//
//    }
//
//    @Test
//    @WithMockUser
//    public void testSearchCompanyEinValid() throws Exception{
//        BDDMockito.given(this.companyService.searchByEin(Mockito.anyString())).willReturn(Optional.of(this.getCompanyData()));
//
//        mvc.perform(MockMvcRequestBuilders.get(SEARCH_COMPANY_EIN_URL + EIN)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.id").value(ID))
//                .andExpect(jsonPath("$.data.businessName", equalTo(BUSINESS_NAME)))
//                .andExpect(jsonPath("$.data.ein", equalTo(EIN)))
//                .andExpect(jsonPath("$.errors").isEmpty());
//    }
//
//    private Company getCompanyData() {
//        Company company = new Company();
//        company.setId(ID);
//        company.setBusinessName(BUSINESS_NAME);
//        company.setEin(EIN);
//        return company;
//    }
//
//
//}

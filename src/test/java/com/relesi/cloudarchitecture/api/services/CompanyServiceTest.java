package com.relesi.cloudarchitecture.api.services;


import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceTest {

    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    private static final String EIN = "97912798000180";

    @Before
    public void setUp() throws Exception {
        BDDMockito.given(this.companyRepository.findByEin(Mockito.anyString())).willReturn(new Company());
        BDDMockito.given(this.companyRepository.save(Mockito.any(Company.class))).willReturn(new Company());
    }

    @Test
    public void testSearchCompanyByEin(){
        final Optional<Company> company = this.companyService.searchByEin(EIN);
        assertTrue(company.isPresent());
    }

    @Test
    public void testPersistCompany(){
         Company company = this.companyService.persist(new Company());
         assertNotNull(company);
    }

}

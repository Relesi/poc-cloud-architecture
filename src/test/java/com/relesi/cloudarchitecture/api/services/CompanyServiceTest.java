package com.relesi.cloudarchitecture.api.services;


import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
}

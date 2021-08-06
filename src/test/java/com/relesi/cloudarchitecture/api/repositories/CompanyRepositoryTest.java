//package com.relesi.cloudarchitecture.api.repositories;
//
//import com.relesi.cloudarchitecture.api.entities.Company;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
//public class CompanyRepositoryTest {
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    private static final String EIN = "77185751000154";
//
//    @Before
//    public void setUp() throws Exception {
//        Company company = new Company();
//        company.setBusinessName("Example Company");
//        company.setEin(EIN);
//        this.companyRepository.save(company);
//    }
//
//    @After
//    public final void tearDown() {
//        this.companyRepository.deleteAll();
//    }
//
//    @Test
//    public void testSearchByEin() {
//        Company company = this.companyRepository.findByEin(EIN);
//        assertEquals(EIN, company.getEin());
//
//    }
//
//}

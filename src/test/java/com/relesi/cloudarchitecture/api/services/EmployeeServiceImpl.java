//package com.relesi.cloudarchitecture.api.services;
//
//
//import com.relesi.cloudarchitecture.api.entities.Company;
//import com.relesi.cloudarchitecture.api.entities.Employee;
//import com.relesi.cloudarchitecture.api.repositories.EmployeeRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.BDDMockito;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
//public class EmployeeServiceImpl {
//
//    @MockBean
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    private static final String SSN = "44966682037";
//
//    @Before
//    public void setUp() throws Exception {
//        BDDMockito.given(this.employeeRepository.save(Mockito.any(Employee.class))).willReturn(new Employee());
//        BDDMockito.given(this.employeeRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Employee()));
//        BDDMockito.given(this.employeeRepository.findByEmail(Mockito.anyString())).willReturn(new Employee());
//        BDDMockito.given(this.employeeRepository.findBySsn(Mockito.anyString())).willReturn(new Employee());
//
//    }
//
//    @Test
//    public void testPersistEmployee(){
//        Employee employee = this.employeeService.persist(new Employee());
//        assertNotNull(employee);
//    }
//
//    @Test
//    public void testSearchEmployeeById(){
//        final Optional<Employee> employee = this.employeeService.searchById(1L);
//        assertTrue(employee.isPresent());
//    }
//
//    @Test
//    public void testSearchEmployeeByEmail(){
//        final Optional<Employee> employee = this.employeeService.searchByEmail("email@email.com");
//        assertTrue(employee.isPresent());
//    }
//
//    @Test
//    public void testSearchEmployeeBySsn(){
//        final Optional<Employee> employee = this.employeeService.searchBySsn("51019433043");
//        assertTrue(employee.isPresent());
//    }
//
//
//}

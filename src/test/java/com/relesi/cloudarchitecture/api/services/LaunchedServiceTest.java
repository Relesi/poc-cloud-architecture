package com.relesi.cloudarchitecture.api.services;

import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.repositories.LaunchedRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LaunchedServiceTest {

    @MockBean
    private LaunchedRepository launchedRepository;

    @Autowired
    private LaunchedService launchedService;

    @Before
    public void setUp() throws Exception {

        BDDMockito.given(this.launchedRepository.findByEmployeeId(Mockito.anyLong(),
                Mockito.any(PageRequest.class))).willReturn(new PageImpl<Launched>(new ArrayList<Launched>()));

        BDDMockito.given(this.launchedRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Launched()));
        BDDMockito.given(this.launchedRepository.save(Mockito.any(Launched.class))).willReturn(new Launched());
    }




    @Test
    public void testSearchLaunchedById(){
        final Optional<Launched> launched = this.launchedService.searchById(1L);
        assertNotNull(launched);

    }

    @Test
    public void testPersistLaunched(){
        Launched launched = this.launchedService.persist(new Launched());
        assertNotNull(launched);
    }



}

package com.relesi.cloudarchitecture.api.services.impl;

import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.repositories.LaunchedRepository;
import com.relesi.cloudarchitecture.api.services.LaunchedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import java.util.Optional;

@Service
public class LaunchedServiceImpl implements LaunchedService {

    private static final Logger log = LoggerFactory.getLogger(LaunchedServiceImpl.class);

    @Autowired
    private LaunchedRepository launchedRepository;



    public Page<Launched> searchByEmployeeId(Long employeeId, PageRequest pageRequest) {
        log.info("Searching for launched for employee ID {}", employeeId);
        return this.launchedRepository.findByEmployeeId(employeeId, pageRequest);
    }

    @Cacheable("launchedById")
    public Optional<Launched> searchById(Long id) {
        log.info("Searching for launched by ID {}", id);
        return this.launchedRepository.findById(id);
    }

    @CachePut("launchedById")
    public Launched persist(Launched launched) {
        log.info("Persisting a Launched: {}", launched);
        return this.launchedRepository.save(launched);
    }

    @Override
    public void remove(Long id) {
        log.info("Removed a Launched by ID: {}", id);
        this.launchedRepository.deleteById(id);

    }
}

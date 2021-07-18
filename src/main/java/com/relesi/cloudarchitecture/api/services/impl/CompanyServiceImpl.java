package com.relesi.cloudarchitecture.api.services.impl;

import java.util.Optional;

import com.relesi.cloudarchitecture.api.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.services.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> searchByEin(String ein) {
        log.info("Looking for a company for the EIN {}", ein);
        return Optional.ofNullable(companyRepository.findByEin(ein));
    }

    @Override
    public Company persist(Company company) {
        log.info("Persisting Company: {}", company);
        return companyRepository.save(company);
    }
}

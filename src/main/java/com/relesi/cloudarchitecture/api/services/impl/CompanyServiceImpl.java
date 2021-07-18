package com.relesi.cloudarchitecture.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.relesi.cloudarchitecture.api.entities.Company;
import com.relesi.cloudarchitecture.api.services.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Override
    public Optional<Company> searchByEin(String ein) {
        return Optional.empty();
    }

    @Override
    public Company persist(Company company) {
        return null;
    }
}

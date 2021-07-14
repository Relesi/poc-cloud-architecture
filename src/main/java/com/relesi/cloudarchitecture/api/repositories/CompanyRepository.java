package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Transactional(readOnly = true)
    Company findByEin(String ein);
}

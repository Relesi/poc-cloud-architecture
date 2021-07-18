package com.relesi.cloudarchitecture.api.services;

import com.relesi.cloudarchitecture.api.entities.Company;

import java.util.Optional;

public interface CompanyService{

    /***
     * Return a company given a EIN
     *
     * @param ein
     * @return Optional<Company>
     *
     * @autor Renato.Lessa
     */

    Optional<Company> searchByEin(String ein);

    /***
     * Register a new company in the database.
     *
     * @param company
     * @return Company
     *
     * @autor Renato.Lessa
     *
     */

    Company persist(Company company);

}




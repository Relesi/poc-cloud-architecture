package com.relesi.cloudarchitecture.api.services;

import com.relesi.cloudarchitecture.api.entities.Launched;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface LaunchedService {

    /***
     * Returns a paging list of an employee's entries.
     *
     * @param employeeId
     * @param pageRequest
     * @return Page<Launched>
     */
    Page<Launched> searchByEmployeeId(Long employeeId, PageRequest pageRequest);

    /***
     * Returns a launched by Id.
     * @param id
     * @return Optional<Launched>
     */
    Optional<Launched> searchById(Long id);

    /***
     * Persist a launched in the data base
     *
     * @param launched
     * @return Launched
     */
    Launched persist(Launched launched);

    /***
     * Remove a launched in the data base
     *
     * @param id
     */
    void remove(Long id);

}

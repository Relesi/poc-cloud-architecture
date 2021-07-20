package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Launched;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@NamedQueries({
        @NamedQuery(name = "LaunchedRepository.findByEmployeeId",
                query = "SELECT lanc FROM Launched lanc WHERE lanc.employee.id = :employee") })
public interface LaunchedRepository extends JpaRepository<Launched, Long> {

    List<Launched> findByEmployeeId(@Param("employeeId") Long employeeId);
    Page<Launched> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
}



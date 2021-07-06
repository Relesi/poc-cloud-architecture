package com.relesi.cloudarchitecture.api.repositories;

import com.relesi.cloudarchitecture.api.entities.Launched;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaunchedRepository extends JpaRepository<Launched, Long> {


    List<Launched> findByEmployeeId(@Param("employeeId") Long employeeId);

    Page<Launched> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);



}

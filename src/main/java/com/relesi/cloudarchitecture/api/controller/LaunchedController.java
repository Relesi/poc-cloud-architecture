package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.dtos.LaunchedDto;
import com.relesi.cloudarchitecture.api.entities.Launched;
import org.springframework.data.domain.Sort.Direction;

import com.relesi.cloudarchitecture.api.Response.Response;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import com.relesi.cloudarchitecture.api.services.LaunchedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/api/launched")
@CrossOrigin(origins = "*")
public class LaunchedController {

    private static final Logger log = LoggerFactory.getLogger(LaunchedController.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private LaunchedService launchedService;

    @Autowired
    private EmployeeService employeeService;


    @Value("${pagination.qty_by_page}")
    private int qtdByPage;


    public LaunchedController(){

    }

    @GetMapping(value = "/employee/{employeeId}")
    public ResponseEntity<Response<Page<LaunchedDto>>> listByEmployeeId(
            @PathVariable("employeeId") Long employeeId,
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {
        log.info("Search launched by ID of employee: {}, page: {}", employeeId, pag);
        Response<Page<LaunchedDto>> response = new Response<Page<LaunchedDto>>();

        PageRequest pageRequest = new PageRequest(pag, this.qtdByPage, Direction.valueOf(dir), ord);

        Page<Launched> launcheds = this.launchedService.searchByEmployeeId(employeeId, pageRequest);
        Page<LaunchedDto> launchedDtos = launcheds.map(launched -> this.convertLaunchedDto(launched));

        response.setData(launchedDtos);
        return ResponseEntity.ok(response);


    }

    /***
     * Convert a entity Launched to your respective DTO.
     *
     * @param launched
     * @return
     */
    private LaunchedDto convertLaunchedDto(Launched launched) {

        LaunchedDto launchedDto =  new LaunchedDto();
        launchedDto.setId(Optional.of(launched.getId()));
        launchedDto.setData(this.dateFormat.format(launched.getCreationDate()));
        launchedDto.setType(launched.getType().toString());
        launchedDto.setDescription(launched.getDescription());
        launchedDto.setLocalization(launched.getDescription());
        launchedDto.setEmployeeId(launched.getEmployee().getId());

        return launchedDto;
    }


}

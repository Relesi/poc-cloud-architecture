package com.relesi.cloudarchitecture.api.controller;

import com.relesi.cloudarchitecture.api.dtos.LaunchedDto;
import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.entities.Launched;
import com.relesi.cloudarchitecture.api.enums.TypeEnum;
import org.springframework.data.domain.Sort.Direction;

import org.apache.commons.lang3.EnumUtils;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
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


    public LaunchedController() {

    }

    /***
     * Return listing of launched of an employee.
     *
     * @param employeeId
     * @param pag
     * @param ord
     * @param dir
     * @return ResponseEntity<Response<LaunchedDto>
     */
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
     * Return a launched by ID.
     *
     * @param id
     * @return ResponseEntity<Response<LaunchedDto>>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<LaunchedDto>> listById(@PathVariable("id") Long id) {
        log.info("Search launched by ID: {}", id);
        Response<LaunchedDto> response = new Response<LaunchedDto>();
        Optional<Launched> launched = this.launchedService.searchById(id);

        if (!launched.isPresent()) {
            log.info("Not found Launched for ID: {}", id);
            response.getErrors().add("Not found Launched for ID: {} " + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.convertLaunchedDto(launched.get()));
        return ResponseEntity.ok(response);
    }

    /***
     * Adds a new Launched.
     *
     * @param launchedDto
     * @param result
     * @return ResponseEntity<Response<LaunchedDto>>
     * @throws ParseException
     */
    @PostMapping
    public ResponseEntity<Response<LaunchedDto>> add(@Valid @RequestBody LaunchedDto launchedDto,
                                                     BindingResult result) throws ParseException {
        log.info("Launched adding.: {}", launchedDto.toString());
        Response<LaunchedDto> response = new Response<LaunchedDto>();
        validateEmployee(launchedDto, result);
        Launched launched = this.convertDtoToLaunched(launchedDto, result);

        if (result.hasErrors()) {
            log.error("Error validating Launched: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        launched = this.launchedService.persist(launched);
        response.setData(this.convertLaunchedDto(launched));
        return ResponseEntity.ok(response);

    }

    /***
     * Update data of a launched.
     *
     * @param id
     * @param launchedDto
     * @param result
     * @return ResponseEntity<Response<Launched>>
     * @throws ParseException
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<LaunchedDto>> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody LaunchedDto launchedDto, BindingResult result) throws ParseException {
        log.info("Launched Updating: {}", launchedDto.toString());
        Response<LaunchedDto> response = new Response<LaunchedDto>();
        validateEmployee(launchedDto, result);
        launchedDto.setId(Optional.of(id));
        Launched launched = this.convertDtoToLaunched(launchedDto, result);

        if (result.hasErrors()) {
            log.error("Error validating Launched: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        launched = this.launchedService.persist(launched);
        response.setData(this.convertLaunchedDto(launched));
        return ResponseEntity.ok(response);

    }

    /***
     * Remove a launched by ID.
     *
     * @param id
     * @return ResponseEntity<Response<Launched>>
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> remove(@PathVariable("id") Long id) {
        log.info("Removendo lançamento: {}", id);
        Response<String> response = new Response<String>();
        Optional<Launched> launched = this.launchedService.searchById(id);

        if (!launched.isPresent()) {
            log.info("Error removing due to posting ID: {} being invalid.", id);
            response.getErrors().add("Error removing release. Record not found for id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.launchedService.remove(id);
        return ResponseEntity.ok(new Response<String>());

    }

    /***
     * Convert a LaunchedDto to a entity Launched.
     *
     * @param launchedDto
     * @param result
     * @return
     * @throws ParseException
     */
    private Launched convertDtoToLaunched(LaunchedDto launchedDto, BindingResult result) throws ParseException {
        Launched launched = new Launched();

        if (launchedDto.getId().isPresent()) {
            Optional<Launched> lanc = this.launchedService.searchById(launchedDto.getId().get());
            if (lanc.isPresent()) {
                launched = lanc.get();
            } else {
                result.addError(new ObjectError("launched", "Launched not found"));
            }
        } else {
            launched.setEmployee(new Employee());
            launched.getEmployee().setId(launchedDto.getEmployeeId());
        }

        launched.setDescription(launchedDto.getDescription());
        launched.setLocalization(launchedDto.getLocalization());
        launched.setCurrentDate(this.dateFormat.parse(launchedDto.getData()));

        if (EnumUtils.isValidEnum(TypeEnum.class, launchedDto.getType())) {
            launched.setType(TypeEnum.valueOf(launchedDto.getType()));
        } else {
            result.addError(new ObjectError("type", "Invalid type."));
        }
        return launched;
    }

    /***
     * Validates an employee, verifying that he is existing and valid in the system.
     *
     * @param launchedDto
     * @param result
     */
    private void validateEmployee(LaunchedDto launchedDto, BindingResult result) {
        if (launchedDto.getEmployeeId() == null) {
            result.addError(new ObjectError("employee", "Employee not informed."));
            return;
        }

        log.info("Validating employee ID {}: ", launchedDto.getEmployeeId());
        Optional<Employee> employee = this.employeeService.searchById(launchedDto.getEmployeeId());
        if (!employee.isPresent()) {
            result.addError(new ObjectError("employee", "Employee not found non-existent ID."));

        }
    }


    /***
     * Convert a entity Launched to your respective DTO.
     *
     * @param launched
     * @return LaunchedDto
     */
    private LaunchedDto convertLaunchedDto(Launched launched) {

        LaunchedDto launchedDto = new LaunchedDto();
        launchedDto.setId(Optional.of(launched.getId()));
        launchedDto.setData(this.dateFormat.format(launched.getCreationDate()));
        launchedDto.setType(launched.getType().toString());
        launchedDto.setDescription(launched.getDescription());
        launchedDto.setLocalization(launched.getDescription());
        launchedDto.setEmployeeId(launched.getEmployee().getId());

        return launchedDto;
    }


}

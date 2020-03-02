package com.mission4.payroll.controller;

import com.mission4.payroll.model.request.CreateEmployeeRequest;
import com.mission4.payroll.model.request.UpdateEmployeeRequest;
import com.mission4.payroll.model.response.CreateEmployeeResponse;
import com.mission4.payroll.model.response.GetAllEmployeeResponse;
import com.mission4.payroll.model.response.GetEmployeeResponse;
import com.mission4.payroll.model.response.UpdateEmployeeResponse;
import com.mission4.payroll.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@Log4j2
public class EmployeeController {
    private GetEmployeeDetailsService getEmployeeDetailsService;
    private GetAllEmployeeDetailsService getAllEmployeeDetailsService;
    private CreateEmployeeService createEmployeeService;
    private UpdateEmployeeService updateEmployeeService;
    private DeleteEmployeeService deleteEmployeeService;

    public EmployeeController(GetEmployeeDetailsService getEmployeeDetailsService,
                              GetAllEmployeeDetailsService getAllEmployeeDetailsService,
                              CreateEmployeeService createEmployeeService,
                              UpdateEmployeeService updateEmployeeService,
                              DeleteEmployeeService deleteEmployeeService) {
        this.getEmployeeDetailsService = getEmployeeDetailsService;
        this.getAllEmployeeDetailsService = getAllEmployeeDetailsService;
        this.createEmployeeService = createEmployeeService;
        this.updateEmployeeService = updateEmployeeService;
        this.deleteEmployeeService = deleteEmployeeService;
    }

    // Aggregate root
    @GetMapping("/employees")
    public GetAllEmployeeResponse all(@RequestHeader(name = "userId", required = true) String userId) {
        log.warn("testing testing");
        log.debug(userId);
        return getAllEmployeeDetailsService.execute();
    }

    @PostMapping("/employees")
    public CreateEmployeeResponse newEmployee(@RequestHeader(name = "userId", required = true) String userId, @RequestBody CreateEmployeeRequest createEmployeeRequest) throws URISyntaxException {
        log.warn("testing testing");
        log.debug(userId);
        return createEmployeeService.execute(createEmployeeRequest);
    }

    // Single item
    @GetMapping("/employees/{id}")
    public GetEmployeeResponse one(@RequestHeader(name = "userId", required = true) String userId, @PathVariable Long id) {
        log.warn("testing testing");
        log.debug(userId);
        return getEmployeeDetailsService.execute(id);
    }

    @PutMapping("/employees/{id}")
    public UpdateEmployeeResponse replaceEmployee(@RequestHeader(name = "userId", required = true) String userId,
                                                  @RequestBody UpdateEmployeeRequest updateEmployeeRequest,
                                                  @PathVariable Long id) throws URISyntaxException {
        log.warn("testing testing");
        log.debug(userId);
        return updateEmployeeService.execute(updateEmployeeRequest, id);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@RequestHeader(name = "userId", required = true) String userId,
                                                 @PathVariable Long id) {
        log.warn("testing testing");
        log.debug(userId);
        return deleteEmployeeService.execute(id);
    }
}

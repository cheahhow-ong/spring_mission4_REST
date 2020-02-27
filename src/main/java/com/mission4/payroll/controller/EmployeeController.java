package com.mission4.payroll.controller;

import com.mission4.payroll.model.request.CreateEmployeeRequest;
import com.mission4.payroll.model.request.UpdateEmployeeRequest;
import com.mission4.payroll.model.response.*;
import com.mission4.payroll.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
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
    public GetAllEmployeeResponse all() {
        return getAllEmployeeDetailsService.execute();
    }

    @PostMapping("/employees")
    public CreateEmployeeResponse newEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) throws URISyntaxException {
        return createEmployeeService.execute(createEmployeeRequest);
    }

    // Single item
    @GetMapping("/employees/{id}")
    public GetEmployeeResponse one(@PathVariable Long id) {
        return getEmployeeDetailsService.execute(id);
    }

    @PutMapping("/employees/{id}")
    public UpdateEmployeeResponse replaceEmployee(@RequestBody UpdateEmployeeRequest updateEmployeeRequest,
                                                  @PathVariable Long id) throws URISyntaxException {
        return updateEmployeeService.execute(updateEmployeeRequest, id);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return deleteEmployeeService.execute(id);
    }
}

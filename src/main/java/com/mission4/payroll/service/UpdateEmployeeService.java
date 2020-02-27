package com.mission4.payroll.service;

import com.mission4.payroll.common.EmployeeNotFoundException;
import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.request.UpdateEmployeeRequest;
import com.mission4.payroll.model.response.UpdateEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UpdateEmployeeService {
    private EmployeeRepository repository;

    public UpdateEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public UpdateEmployeeResponse execute(@RequestBody UpdateEmployeeRequest updateEmployeeRequest, Long id) {
        Employee updatedEmployee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException());

        updatedEmployee.setLastName(updateEmployeeRequest.getLastName());
        updatedEmployee.setFirstName(updateEmployeeRequest.getFirstName());
        updatedEmployee.setRole(updateEmployeeRequest.getRole());

        Employee savedEmployee = repository.save(updatedEmployee);

        return UpdateEmployeeResponse.builder()
                .FirstName(savedEmployee.getFirstName())
                .LastName(savedEmployee.getLastName())
                .Role(savedEmployee.getRole())
                .build();
    }
}

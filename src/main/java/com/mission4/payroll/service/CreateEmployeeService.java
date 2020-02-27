package com.mission4.payroll.service;

import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.request.CreateEmployeeRequest;
import com.mission4.payroll.model.response.CreateEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CreateEmployeeService {
    private EmployeeRepository repository;

    public CreateEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public CreateEmployeeResponse execute(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        Employee employee = Employee.builder()
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .role(createEmployeeRequest.getRole())
                .build();

        Employee savedEmployee = repository.save(employee);

        return CreateEmployeeResponse.builder()
                .FirstName(savedEmployee.getFirstName())
                .LastName(savedEmployee.getLastName())
                .Role(savedEmployee.getRole())
                .build();
    }
}

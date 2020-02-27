package com.mission4.payroll.service;

import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.response.GetAllEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllEmployeeDetailsService {
    private EmployeeRepository repository;

    public GetAllEmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.repository = employeeRepository;
    }

    public GetAllEmployeeResponse execute() {
        List<Employee> employeesList = repository.findAll();
        List<GetAllEmployeeResponse.EmployeeResponse> getAllEmployeeResponses = employeesList.stream().map(
                it -> GetAllEmployeeResponse.EmployeeResponse.builder()
                        .Id(it.getId().toString())
                        .FirstName(it.getFirstName())
                        .LastName(it.getLastName())
                        .Role(it.getRole())
                        .build()
        ).collect(Collectors.toList());
        return GetAllEmployeeResponse.builder()
                .allEmployeeDetails(getAllEmployeeResponses)
                .build();
    }
}

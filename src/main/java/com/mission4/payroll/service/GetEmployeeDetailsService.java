package com.mission4.payroll.service;

import com.mission4.payroll.common.EmployeeNotFoundException;
import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.response.GetEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeDetailsService {
    private EmployeeRepository repository;

    public GetEmployeeDetailsService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public GetEmployeeResponse execute(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException());

        return GetEmployeeResponse.builder()
                .Id(employee.getId().toString())
                .FirstName(employee.getFirstName())
                .LastName(employee.getLastName())
                .Role(employee.getRole())
                .build();
    }
}

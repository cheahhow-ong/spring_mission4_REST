package com.mission4.payroll.service;

import com.mission4.payroll.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DeleteEmployeeService {
    private EmployeeRepository repository;

    public DeleteEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<String> execute(@RequestBody Long id) {
        repository.deleteById(id);

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
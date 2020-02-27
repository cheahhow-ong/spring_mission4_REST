package com.mission4.payroll.common;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
        super("Could not find employee");
    }
}

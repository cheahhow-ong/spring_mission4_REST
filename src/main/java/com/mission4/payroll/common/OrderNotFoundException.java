package com.mission4.payroll.common;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) { super("Could not find order " + id); }
}

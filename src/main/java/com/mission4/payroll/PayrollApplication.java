package com.mission4.payroll;

import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class PayrollApplication {
	public static void main(String[] args) {
		SpringApplication.run(PayrollApplication.class, args);
	}
}

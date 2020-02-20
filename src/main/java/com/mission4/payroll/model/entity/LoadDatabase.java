package com.mission4.payroll.model.entity;

import com.mission4.payroll.repository.EmployeeRepository;
import com.mission4.payroll.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository,
                                   OrderRepository orderRepository) {

        orderRepository.save(new Order("Macbook", Status.COMPLETED));
        orderRepository.save(new Order("iphone", Status.IN_PROGRESS));

        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));
        };
    }
}
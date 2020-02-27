package com.mission4.payroll;

import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.repository.EmployeeRepository;
import lombok.var;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class employeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private Employee employee;

    @Before
    public void setup() {
        // Given
        employee = Employee.builder()
                .firstName("lebron")
                .lastName("james")
                .role("nba")
                .build();
        testEntityManager.persistAndFlush(employee);
    }

    @Test
    public void getOne() {
        var actual = employeeRepository.findById(1L).get();

        // Then
        Assert.assertEquals(employee, actual);
    }

    @Test
    public void getAll() {
        var actual = employeeRepository.findAll().get(0);

        // Then
        Assert.assertEquals(employee, actual);
    }

    @Test
    public void createOne() {
        var actual = employeeRepository.save(employee);

        // Then
        Assert.assertEquals(employee, actual);
    }
}

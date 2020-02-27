package com.mission4.payroll;

import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.request.CreateEmployeeRequest;
import com.mission4.payroll.model.response.CreateEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import com.mission4.payroll.service.CreateEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class createEmployeeServiceTest {
    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    private CreateEmployeeService createEmployeeService = new CreateEmployeeService(employeeRepository);

    private CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    private Employee employee;

    @Before
    public void setup() {
        employee = Employee.builder()
                .id(1L)
                .firstName("lebron")
                .lastName("james")
                .role("nba")
                .build();
    }

    @Test
    public void createEmployeeServiceTest() {
        // Given
        createEmployeeRequest = CreateEmployeeRequest.builder()
                .FirstName("lebron")
                .LastName("james")
                .Role("nba")
                .build();

        CreateEmployeeResponse employeeResponse = CreateEmployeeResponse.builder()
                .FirstName("lebron")
                .LastName("james")
                .Role("nba")
                .build();

        // When
        when(employeeRepository.save(any())).thenReturn(employee);

        // Then
        CreateEmployeeResponse actual = createEmployeeService.execute(createEmployeeRequest);
        assertThat(actual).isEqualToComparingFieldByField(employeeResponse);
    }
}

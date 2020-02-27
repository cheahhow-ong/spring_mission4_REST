package com.mission4.payroll;

import com.mission4.payroll.common.EmployeeNotFoundException;
import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.request.CreateEmployeeRequest;
import com.mission4.payroll.model.request.UpdateEmployeeRequest;
import com.mission4.payroll.model.response.CreateEmployeeResponse;
import com.mission4.payroll.model.response.UpdateEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import com.mission4.payroll.service.CreateEmployeeService;
import com.mission4.payroll.service.UpdateEmployeeService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class updateEmployeeServiceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    private UpdateEmployeeService updateEmployeeService = new UpdateEmployeeService(employeeRepository);

    private UpdateEmployeeRequest updateEmployeeRequest = new UpdateEmployeeRequest();
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
    public void updateEmployeeServiceTest() {
        // Given
        updateEmployeeRequest = UpdateEmployeeRequest.builder()
                .FirstName("lebron")
                .LastName("james")
                .Role("nba")
                .build();

        UpdateEmployeeResponse  employeeResponse = UpdateEmployeeResponse.builder()
                .FirstName("lebron")
                .LastName("james")
                .Role("nba")
                .build();

        // When
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any())).thenReturn(employee);

        // Then
        UpdateEmployeeResponse actual = updateEmployeeService.execute(updateEmployeeRequest, 1L);
        assertThat(actual).isEqualToComparingFieldByField(employeeResponse);
    }

    @Test
    public void updateEmployeeServiceExceptionTest() {
        // Then
        expectedException.expect(CoreMatchers.instanceOf(EmployeeNotFoundException.class));
//        expectedException.expect(Matchers.hasProperty("Could not find employee"));
        updateEmployeeService.execute(updateEmployeeRequest, 5L);
    }
}

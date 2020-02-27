package com.mission4.payroll;

import com.mission4.payroll.common.EmployeeNotFoundException;
import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.response.GetEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import com.mission4.payroll.service.GetEmployeeDetailsService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class getEmployeeDetailsServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    private GetEmployeeDetailsService getEmployeeDetailsService = new GetEmployeeDetailsService(employeeRepository);

    private Employee employee;

    @Before
    public void setup() {
        // Given
        employee = Employee.builder()
                .id(1L)
                .firstName("lebron")
                .lastName("james")
                .role("nba")
                .build();
    }

    @Test
    public void getEmployeeDetailsServiceTest() {
        // Given
        GetEmployeeResponse employeeResponse = GetEmployeeResponse.builder()
                .Id("1")
                .FirstName("lebron")
                .LastName("james")
                .Role("nba")
                .build();

        // When
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Then
        GetEmployeeResponse actual = getEmployeeDetailsService.execute(1L);
        assertThat(actual).isEqualToComparingFieldByField(employeeResponse);
    }

    @Test
    public void getEmployeeDetailsServiceExceptionTest() {
        // Then
        expectedException.expect(CoreMatchers.instanceOf(EmployeeNotFoundException.class));
//        expectedException.expect(Matchers.hasProperty("Could not find employee"));
        getEmployeeDetailsService.execute(6L);
    }
}
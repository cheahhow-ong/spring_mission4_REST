package com.mission4.payroll;

import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.response.GetAllEmployeeResponse;
import com.mission4.payroll.repository.EmployeeRepository;
import com.mission4.payroll.service.GetAllEmployeeDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class getAllEmployeeDetailsServiceTest {

    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    private GetAllEmployeeDetailsService getAllEmployeeDetailsService = new GetAllEmployeeDetailsService(employeeRepository);

    private List<Employee> employees = new ArrayList<>();

    @Before
    public void setup() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("lebron")
                .lastName("james")
                .role("nba")
                .build();

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("michael")
                .lastName("jordan")
                .role("football")
                .build();

        employees.add(employee);
        employees.add(employee1);
    }

    @Test
    public void getAllEmployeeDetailsServiceTest() {
        // Given
        GetAllEmployeeResponse.EmployeeResponse employeeResponse = GetAllEmployeeResponse.EmployeeResponse.builder()
                .Id("1")
                .FirstName("lebron")
                .LastName("james")
                .Role("nba")
                .build();
        GetAllEmployeeResponse.EmployeeResponse employeeResponse1 = GetAllEmployeeResponse.EmployeeResponse.builder()
                .Id("2")
                .FirstName("michael")
                .LastName("jordan")
                .Role("football")
                .build();

        List<GetAllEmployeeResponse.EmployeeResponse> employeeResponses = new ArrayList<>();
        employeeResponses.add(employeeResponse);
        employeeResponses.add(employeeResponse1);

        GetAllEmployeeResponse getAllEmployeeResponse = GetAllEmployeeResponse.builder()
                .allEmployeeDetails(employeeResponses)
                .build();

        // When
        when(employeeRepository.findAll()).thenReturn(employees);

        // Then
        GetAllEmployeeResponse actual = getAllEmployeeDetailsService.execute();
        assertThat(actual).isEqualToComparingFieldByField(getAllEmployeeResponse);
    }
}
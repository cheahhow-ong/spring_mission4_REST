package com.mission4.payroll;

import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.repository.EmployeeRepository;
import com.mission4.payroll.service.DeleteEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class deleteEmployeeServiceTest {
    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    private DeleteEmployeeService deleteEmployeeService = new DeleteEmployeeService(employeeRepository);

    private Employee employee;

    private ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);

    @Captor
    private ArgumentCaptor<ResponseEntity> argumentCaptor;

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
        // When
        doNothing().when(employeeRepository).deleteById(any());

        // Then
        ResponseEntity<String> actual = deleteEmployeeService.execute(1L);
        assertThat(actual).isEqualToComparingFieldByField(responseEntity);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}

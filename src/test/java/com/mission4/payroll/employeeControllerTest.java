package com.mission4.payroll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mission4.payroll.controller.EmployeeController;
import com.mission4.payroll.model.entity.Employee;
import com.mission4.payroll.model.request.CreateEmployeeRequest;
import com.mission4.payroll.model.request.UpdateEmployeeRequest;
import com.mission4.payroll.model.response.CreateEmployeeResponse;
import com.mission4.payroll.model.response.GetAllEmployeeResponse;
import com.mission4.payroll.model.response.GetEmployeeResponse;
import com.mission4.payroll.model.response.UpdateEmployeeResponse;
import com.mission4.payroll.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class employeeControllerTest {
    private GetEmployeeDetailsService getEmployeeDetailsService = mock(GetEmployeeDetailsService.class);
    private GetAllEmployeeDetailsService getAllEmployeeDetailsService = mock(GetAllEmployeeDetailsService.class);
    private CreateEmployeeService createEmployeeService = mock(CreateEmployeeService.class);
    private UpdateEmployeeService updateEmployeeService = mock(UpdateEmployeeService.class);
    private DeleteEmployeeService deleteEmployeeService = mock(DeleteEmployeeService.class);

    private MockMvc mvc;

    public EmployeeController employeeController = new EmployeeController(
            getEmployeeDetailsService,
            getAllEmployeeDetailsService,
            createEmployeeService,
            updateEmployeeService,
            deleteEmployeeService);

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getAllEmployeesAPI() throws Exception {
        //given
        GetAllEmployeeResponse.EmployeeResponse employeeResponse = GetAllEmployeeResponse.EmployeeResponse.builder()
                .FirstName("first")
                .LastName("last")
                .Role("some role")
                .build();
        GetAllEmployeeResponse.EmployeeResponse employeeResponse1 = GetAllEmployeeResponse.EmployeeResponse.builder()
                .FirstName("first1")
                .LastName("last1")
                .Role("some role1")
                .build();
        List<GetAllEmployeeResponse.EmployeeResponse> employeeResponses = new ArrayList<>();
        employeeResponses.add(employeeResponse);
        employeeResponses.add(employeeResponse1);

        GetAllEmployeeResponse getAllEmployeeResponse = GetAllEmployeeResponse.builder()
                .allEmployeeDetails(employeeResponses)
                .build();

        when(getAllEmployeeDetailsService.execute()).thenReturn(getAllEmployeeResponse);

        //Then
        mvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsBytes())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.allEmployeeDetails").exists())
                .andDo(print());
    }

    @Test
    public void getOneEmployeeAPI() throws Exception {
        //given
        GetEmployeeResponse employeeResponse = GetEmployeeResponse.builder()
                .FirstName("First")
                .LastName("Second")
                .Role("Crazy Role")
                .build();

        when(getEmployeeDetailsService.execute(1L)).thenReturn(employeeResponse);

        //Then
        mvc.perform(get("/employees/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("First"))
                .andDo(print());
    }

    @Test
    public void createEmployeeAPI() throws Exception {
        //given
        CreateEmployeeRequest employeeRequest = CreateEmployeeRequest.builder()
                .FirstName("First1")
                .LastName("Second2")
                .Role("crazy role 2")
                .build();

        CreateEmployeeResponse employeeResponse = CreateEmployeeResponse.builder()
                .FirstName("First1")
                .LastName("Second2")
                .Role("crazy role 2")
                .build();

        when(createEmployeeService.execute(employeeRequest)).thenReturn(employeeResponse);

        //Then
        mvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employeeRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("First1"))
                .andDo(print());
    }

    @Test
    public void updateEmployeeAPI() throws Exception {
        //given
        Employee employee = Employee.builder()
                .firstName("First1")
                .lastName("Second2")
                .role("crazy role 2")
                .build();

        UpdateEmployeeRequest employeeRequest = UpdateEmployeeRequest.builder()
                .FirstName("steph")
                .LastName("curry")
                .Role("nba")
                .build();

        UpdateEmployeeResponse employeeResponse = UpdateEmployeeResponse.builder()
                .FirstName("steph")
                .LastName("curry")
                .Role("nba")
                .build();

        when(updateEmployeeService.execute(employeeRequest, 1L)).thenReturn(employeeResponse);

        //Then
        mvc.perform(put("/employees/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employeeRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("steph"))
                .andDo(print());
    }
}
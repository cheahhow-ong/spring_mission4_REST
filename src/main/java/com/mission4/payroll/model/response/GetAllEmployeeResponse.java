package com.mission4.payroll.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployeeResponse {

    private List<EmployeeResponse> allEmployeeDetails;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeResponse {
        private String Id;
        private String FirstName;
        private String LastName;
        private String Role;
    }
}

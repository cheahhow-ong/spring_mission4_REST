package com.mission4.payroll.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeResponse {
    private String Id;
    private String FirstName;
    private String LastName;
    private String Role;
}

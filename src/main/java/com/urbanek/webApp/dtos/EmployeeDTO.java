package com.urbanek.webApp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO extends EmployeeDtoWithoutCompany implements Serializable {
    private CompanyDtoWithoutEmployees company;

}

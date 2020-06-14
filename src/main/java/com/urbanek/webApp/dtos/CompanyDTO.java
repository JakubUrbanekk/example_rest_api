package com.urbanek.webApp.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CompanyDTO extends CompanyDtoWithoutEmployees implements Serializable {
    private List<EmployeeDtoWithoutCompany> employees;

    public CompanyDTO(){
        employees = new ArrayList<>();
    }
}

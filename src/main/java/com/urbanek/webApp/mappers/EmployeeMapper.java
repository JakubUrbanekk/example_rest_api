package com.urbanek.webApp.employees;

import com.urbanek.webApp.companies.Company;
import com.urbanek.webApp.companies.CompanyDTO;
import org.mapstruct.*;

@Mapper(componentModel="spring")
public interface EmployeeMapper {
    EmployeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployee(EmployeeDTO employeeDTO);
    void updateEmployeeFromDto(EmployeeDTO employeeDTO, @MappingTarget Employee employee);
}

package com.urbanek.webApp.companies;


import com.urbanek.webApp.employees.Employee;
import com.urbanek.webApp.employees.EmployeeDTO;
import com.urbanek.webApp.employees.EmployeeDtoWithoutCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toCompanyDTO(Company company);
    Company toCompany(CompanyDTO companyDTO);
    Employee toEmployee(EmployeeDtoWithoutCompany employeeDTO);
    EmployeeDTO toEmployeeDto(EmployeeDtoWithoutCompany employee);
    void updateCompanyFromDto(CompanyDTO companyDTO, @MappingTarget Company company);
}

package com.urbanek.webApp.mappers;


import com.urbanek.webApp.daos.Company;
import com.urbanek.webApp.dtos.CompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toCompanyDTO(Company company);
    Company toCompany(CompanyDTO companyDTO);
    void updateCompanyFromDto(CompanyDTO companyDTO, @MappingTarget Company company);
}

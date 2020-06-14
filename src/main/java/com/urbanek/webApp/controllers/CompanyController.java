package com.urbanek.webApp.controllers;

import com.urbanek.webApp.dtos.CompanyDTO;
import com.urbanek.webApp.services.CompanyService;
import com.urbanek.webApp.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class CompanyController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    public CompanyController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDTO> getCompanies(){
        return companyService.findAll();
    }

    @GetMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO getCompanyById(@PathVariable UUID id){
        return companyService.findByIdentifier(id);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO addCompany(@RequestBody CompanyDTO company){
        return companyService.saveOrUpdate(company);
    }

    @PostMapping("/companies/{id}/employees/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO addEmployeeToCompany(@PathVariable UUID id, @PathVariable UUID employeeId){
        return companyService.addEmployee(id, employeeId);
    }

    @DeleteMapping("/mappers/{companyId}/employees/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromCompany(@PathVariable UUID companyId, @PathVariable UUID employeeId){
        companyService.removeEmployee(companyId, employeeId);
    }
}

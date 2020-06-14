package com.urbanek.webApp.services;

import com.urbanek.webApp.daos.Company;
import com.urbanek.webApp.daos.Employee;
import com.urbanek.webApp.dtos.CompanyDTO;
import com.urbanek.webApp.exceptions.CompanyNotFoundException;
import com.urbanek.webApp.exceptions.EmployeeNotFoundException;
import com.urbanek.webApp.mappers.CompanyMapper;
import com.urbanek.webApp.repositories.CompanyRepository;
import com.urbanek.webApp.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyService {
    private final String COMPANY_NOT_FOUND = "Couldn't find company with id - ";
    private final String EMPLOYEE_NOT_FOUND = "Couldn't find employee with id - ";
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.companyMapper = companyMapper;
    }

    private Company findCompanyFromDb (UUID identifier){
        return companyRepository.findByIdentifier(identifier)
                .orElseThrow(()-> new CompanyNotFoundException(COMPANY_NOT_FOUND + identifier));
    }

    private Employee findEmployeeFromDb(UUID identifier){
        return employeeRepository.findByIdentifier(identifier)
                .orElseThrow(()-> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + identifier));
    }

    public List<CompanyDTO> findAll(){
        return companyRepository.findAll()
                .stream()
                .map(company -> companyMapper.toCompanyDTO(company))
                .collect(Collectors.toList());
    }

    public CompanyDTO findByIdentifier(UUID id) {
        return companyMapper.toCompanyDTO(findCompanyFromDb(id));
    }

    public CompanyDTO saveOrUpdate(CompanyDTO companyDTO){
        Company company = Optional.ofNullable(companyDTO.getIdentifier())
                .map(uuid -> companyRepository.findByIdentifier(uuid)
                        .orElseThrow(()-> new CompanyNotFoundException(COMPANY_NOT_FOUND + uuid)))
                .orElseGet(Company::new);

        companyMapper.updateCompanyFromDto(companyDTO, company);

        log.info("Saving company " + company);

        return companyMapper.toCompanyDTO(companyRepository.save(company));
    }

    public void removeEmployee(UUID companyId, UUID employeeId) {
        Company company = findCompanyFromDb(companyId);
        Employee employee = findEmployeeFromDb(employeeId);

        log.info(String.format("Remove employee - %s from - %s", employee, company));

        company.removeEmployee(employee);
        companyRepository.save(company);
    }

    public CompanyDTO addEmployee(UUID companyId, UUID employeeId){
        Company company = findCompanyFromDb(companyId);
        Employee employee = findEmployeeFromDb(employeeId);

        log.info(String.format("Setting employee to company - %s", employee));
        company.addEmployee(employee);

        companyRepository.save(company);
        log.info(String.format("Created company - %s", company));

        return companyMapper.toCompanyDTO(company);
    }

//    public CompanyDTO update(UUID id, CompanyDTO dto){
//        return companyRepository.findByIdentifier(id)
//                .map(company ->{
//                    company = company.convertToEnity(dto);
//                    return companyRepository.saveOrUpdate(company);
//                })
//                .orElseGet(()-> {
//                    Company company = new Company();
//                    company = company.convertToEnity(dto);
//                    return companyRepository.saveOrUpdate(company);
//                })
//                .convertToDTO();
//    }
}

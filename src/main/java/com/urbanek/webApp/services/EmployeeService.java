package com.urbanek.webApp.services;

import com.urbanek.webApp.daos.Employee;
import com.urbanek.webApp.dtos.EmployeeDTO;
import com.urbanek.webApp.exceptions.EmployeeCityNotFound;
import com.urbanek.webApp.exceptions.EmployeeNotFoundException;
import com.urbanek.webApp.mappers.EmployeeMapper;
import com.urbanek.webApp.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
//transacional
@Slf4j
@Service
public class EmployeeService {
    private final String EMPLOYEE_NOT_FOUND = "Couldn't find employee with id - ";
    private final String EMPLOYEE_CITY_NOT_FOUND = "Couldn't find employee with city - ";
    private final EmployeeRepository repository;
    private final EmployeeMapper employeeMapper;

    private Function <UUID, EmployeeNotFoundException> companyNotFound =
            (uuid -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + uuid));

    public EmployeeService(EmployeeRepository repository, EmployeeMapper employeeMapper) {
        this.repository = repository;
        this.employeeMapper = employeeMapper;
    }


    public List<EmployeeDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());

    }

    public EmployeeDTO findByIdentifier(UUID uuid) {
        Employee employee = repository.findByIdentifier(uuid)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + uuid));

        return employeeMapper.toEmployeeDTO(employee);
    }

    public EmployeeDTO saveOrUpdate(EmployeeDTO employeeDTO) {
        log.info("Creating new user from DTO " + employeeDTO + employeeDTO.getCompany());
        Employee employee = Optional.ofNullable(employeeDTO.getIdentifier())
                .map(uuid -> repository.findByIdentifier(uuid)
                        .orElseThrow(()-> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + uuid)))
                .orElseGet(Employee::new);

        employeeMapper.updateEmployeeFromDto(employeeDTO, employee);
        Employee newEmployee = repository.save(employee);

        log.info("Created employee" + newEmployee);
        return employeeMapper.toEmployeeDTO(newEmployee);
    }

    public void deleteEmployee(UUID id) {
        EmployeeDTO fromDb = findByIdentifier(id);
        log.info("Deleting employee " + fromDb);
        repository.delete(employeeMapper.toEmployee(fromDb));
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public List<EmployeeDTO> findByCity(String cityName) {
        return (repository.findByCity(cityName)
                .orElseThrow(()-> new EmployeeCityNotFound(EMPLOYEE_CITY_NOT_FOUND + cityName))
        .stream()
            .map(employeeMapper::toEmployeeDTO)
        .collect(Collectors.toList()));
    }
}


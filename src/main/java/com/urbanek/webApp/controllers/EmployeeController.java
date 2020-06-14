package com.urbanek.webApp.controllers;

import com.urbanek.webApp.dtos.EmployeeDTO;
import com.urbanek.webApp.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addEmployee(@RequestBody @Valid EmployeeDTO employee){
        return employeeService.saveOrUpdate(employee);
    }

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/city/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployeesByCity(@PathVariable String cityName){
        return employeeService.findByCity(cityName);
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable UUID id){
        return employeeService.findByIdentifier(id);
    }


    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID id){
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("/employees")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        employeeService.deleteAll();
    }
}

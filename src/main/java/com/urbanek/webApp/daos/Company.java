package com.urbanek.webApp.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Slf4j
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(schema = "test", name = "company")
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    @NotNull(message = "company name shouldn't been empty")
    private String name;
    @EqualsAndHashCode.Include
    @Column(name = "identifier", nullable = false)
    private final UUID identifier;
    @ToString.Exclude
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Company(){
        employees = new ArrayList<>();
        identifier = UUID.randomUUID();
    }

    public Company addEmployee(Employee employee){
        employee.setCompany(this);
        employees.add(employee);
        return this;
    }

    public void removeEmployee(Employee employee){
        employee.setCompany(null);
        employees.remove(employee);
    }
}

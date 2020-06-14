package com.urbanek.webApp.daos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Employee", schema = "test",
indexes = { @Index( name = "cityIndex", columnList = "city" )})
@Slf4j
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @EqualsAndHashCode.Include
    @Column(name = "identifier", nullable = false)
    private final UUID identifier;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String lastName;
    @Column(name = "city", nullable = true)
    @Nullable
    private String city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @ToString.Exclude
    private Company company;

    public Employee(){
        identifier = UUID.randomUUID();
    }

}
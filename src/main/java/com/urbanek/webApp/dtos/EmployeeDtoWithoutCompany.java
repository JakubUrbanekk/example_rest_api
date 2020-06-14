package com.urbanek.webApp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDtoWithoutCompany {
    private UUID identifier;
    @NotNull
    private String name;
    @NotNull
    // pesel o va;lidator czy pesel jest poprawny, unikalny
    private String lastName;
    private String city;

}

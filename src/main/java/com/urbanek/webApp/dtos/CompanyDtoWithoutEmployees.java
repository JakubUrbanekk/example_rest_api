package com.urbanek.webApp.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CompanyDtoWithoutEmployees implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private UUID identifier;
}
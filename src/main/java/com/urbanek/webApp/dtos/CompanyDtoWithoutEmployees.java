package com.urbanek.webApp.companies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CompanyDtoWithoutEmployees implements Serializable {
    @NotNull
    @UniqueElements
    private String name;
    private UUID identifier;
}
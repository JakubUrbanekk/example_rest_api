package com.urbanek.webApp.repositories;

import com.urbanek.webApp.daos.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByIdentifier(UUID uuid);
}

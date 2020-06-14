package com.urbanek.webApp.repositories;

import com.urbanek.webApp.daos.Employee;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   // @Query("DELETE FROM Employee e WHERE e.id=:id")
//    Optional<Employee> deleteEmployeeById(@Param("id") long id);
    Optional<Employee> findByIdentifier(UUID identifier);
    Optional<List<Employee>> findByCity(String city);

    @Query("SELECT e FROM Employee e WHERE e.city LIKE :city")
    Optional<List<Employee>> filterByCity(String city);
}

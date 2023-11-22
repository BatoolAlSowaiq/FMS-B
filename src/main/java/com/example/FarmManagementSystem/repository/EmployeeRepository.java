package com.example.FarmManagementSystem.repository;

import com.example.FarmManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.farm.id = :farmId AND " +
            "(e.name LIKE %:query% OR e.position LIKE %:query%)")
    List<Employee> search(@Param("farmId") Integer farmId, @Param("query") String query);
}

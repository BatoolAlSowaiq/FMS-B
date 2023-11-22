package com.example.FarmManagementSystem.repository;

import com.example.FarmManagementSystem.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Integer> {

    // Find by farm name
    Optional<Farm> findByNameIgnoreCase(String name);

    // Find farms by country
    List<Farm> findByLocationCountryIgnoreCase(String country);

    // Find farms by city
    List<Farm> findByLocationCityIgnoreCase(String city);

    // Find farm by both city and country
    List<Farm> findByLocationCityIgnoreCaseAndLocationCountryIgnoreCase(String city, String country);
    @Query("SELECT f FROM Farm f WHERE f.name LIKE %:query% OR f.location.street LIKE %:query% OR f.location.city LIKE %:query% OR f.location.country LIKE %:query%")
    List<Farm> search(@Param("query") String query);
}

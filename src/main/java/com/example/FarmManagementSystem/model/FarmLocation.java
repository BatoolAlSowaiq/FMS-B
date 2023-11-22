package com.example.FarmManagementSystem.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmLocation {
    private String street;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "country is required")
    private String country;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", inclusive = false, message = "Latitude must be greater than -90.0")
    @DecimalMax(value = "90.0", inclusive = false, message = "Latitude must be less than 90.0")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", inclusive = false, message = "Longitude must be greater than -180.0")
    @DecimalMax(value = "180.0", inclusive = false, message = "Longitude must be less than 180.0")
    private Double longitude;

}

package com.example.FarmManagementSystem.controller.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Position is required")
    private String position;
    private String imageUrl;
    @NotNull(message = "Farm ID is required")
    private Integer farmId;
}

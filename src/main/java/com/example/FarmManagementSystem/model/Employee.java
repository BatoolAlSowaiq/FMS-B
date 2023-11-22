package com.example.FarmManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Position is required")
    private String position;
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="farm_id")
    private Farm farm;
}
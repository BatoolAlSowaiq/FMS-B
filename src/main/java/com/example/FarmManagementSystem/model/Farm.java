package com.example.FarmManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Farm name must not be empty!")
    private String name;

    @Embedded
    private FarmLocation location;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Farm(String name, FarmLocation location) {
        this.name = name;
        this.location = location;
    }
}

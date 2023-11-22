package com.example.FarmManagementSystem.services.impl;

import com.example.FarmManagementSystem.controller.DTO.EmployeeDTO;
import com.example.FarmManagementSystem.model.Employee;
import com.example.FarmManagementSystem.model.Farm;
import com.example.FarmManagementSystem.repository.EmployeeRepository;
import com.example.FarmManagementSystem.repository.FarmRepository;
import com.example.FarmManagementSystem.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    //============================================== Connections =======================================================
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FarmRepository farmRepository;

    //============================================== GET method ========================================================
    @Override
    public Employee getEmployeeById(Integer employeeId) {
        // Check if the employee exists
        return  employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

    }

    //============================================== POST method =======================================================
    @Override
    public void saveEmployee(EmployeeDTO employee) {

        // Check if employee already exists
        if (employee.getId() != null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
            if (employeeOptional.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Employee with id " + employee.getId() + " already exists");
        }

        // Get the employee info from the DTO and set them to new employee
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setPosition(employee.getPosition());
        newEmployee.setImageUrl(employee.getImageUrl());
        Farm farm = farmRepository.findById(employee.getFarmId()).get();
        newEmployee.setFarm(farm);

        // Save the employee
        employeeRepository.save(newEmployee);

    }
    //============================================== PUT method ========================================================
    @Override
    public void updateEmployee(EmployeeDTO employee, Integer employeeId) {
        // Check if the employee exists
        Employee employeeFromDB = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        employeeFromDB.setId(employeeId);
        employeeFromDB.setName(employee.getName());
        employeeFromDB.setPosition(employee.getPosition());
        employeeFromDB.setImageUrl(employee.getImageUrl());
        Farm farm = farmRepository.findById(employee.getFarmId()).get();
        employeeFromDB.setFarm(farm);

        // Save the employee
        employeeRepository.save(employeeFromDB);
    }
    //============================================== DELETE method =====================================================
    @Override
    public void deleteEmployee(Integer employeeId) {

        // Check if the employee exists
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        // Delete the employee
        employeeRepository.deleteById(employeeId);
    }

}

package com.example.FarmManagementSystem.services.interfaces;

import com.example.FarmManagementSystem.controller.DTO.EmployeeDTO;
import com.example.FarmManagementSystem.model.Employee;

public interface IEmployeeService {
    //============================================== GET method ========================================================
    public Employee getEmployeeById(Integer employeeId);
    //============================================== POST method =======================================================
    public void saveEmployee(EmployeeDTO employee);
    //============================================== PUT method ========================================================
    public void updateEmployee(EmployeeDTO employee, Integer employeeId);
    //============================================== DELETE method =====================================================
    public void deleteEmployee(Integer employeeId);
}

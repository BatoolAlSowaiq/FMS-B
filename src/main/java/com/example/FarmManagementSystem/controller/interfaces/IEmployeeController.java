package com.example.FarmManagementSystem.controller.interfaces;

import com.example.FarmManagementSystem.controller.DTO.EmployeeDTO;
import com.example.FarmManagementSystem.model.Employee;

import java.util.List;

public interface IEmployeeController {

    //============================================ GET Requests ========================================================
    public Employee getEmployeeById(Integer employeeId);
    public List<Employee> getAllEmployee();
    public List<Employee> searchFarms(Integer farmId,String query);
    //============================================ POST Requests =======================================================
    public void createEmployee(EmployeeDTO employeeDTO);
    //============================================ PUT Requests ========================================================
    public void updateFarm(EmployeeDTO employeeDTO,Integer employeeId);
    //============================================ DELETE Requests =====================================================
    public void deleteEmployee( Integer employeeId);


}

package com.example.FarmManagementSystem.controller.impl;

import com.example.FarmManagementSystem.controller.DTO.EmployeeDTO;
import com.example.FarmManagementSystem.controller.interfaces.IEmployeeController;
import com.example.FarmManagementSystem.model.Employee;
import com.example.FarmManagementSystem.repository.EmployeeRepository;
import com.example.FarmManagementSystem.services.impl.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/Employee")
public class EmployeeController implements IEmployeeController {
    //========================================= Connections  ===========================================================
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    //============================================ GET Requests ========================================================
    @Override
    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    @Override
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    @GetMapping("{farmId}/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> searchFarms(@PathVariable Integer farmId,@RequestParam("q") String query) {
        return employeeRepository.search(farmId,query);
    }
    //============================================ POST Requests =======================================================
    @Override
    @PostMapping("/Add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {employeeService.saveEmployee(employeeDTO);}

    //============================================ PUT Requests ========================================================
    @Override
    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFarm(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Integer employeeId) {
        employeeService.updateEmployee(employeeDTO,employeeId);
    }

    //============================================ DELETE Requests =====================================================
    @Override
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}

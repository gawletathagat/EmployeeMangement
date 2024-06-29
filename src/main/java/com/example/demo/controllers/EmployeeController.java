package com.example.demo.controllers;

// Creating API using MVC structure
/*-Presentation == controller package folder + DTO package
-Service == service package folder
-persistence == Repositories(Interface) package folder + Entities packages */

//GET / employee
//POST /employee
//DELETE / employee /{id}

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {


    private final EmployeeService employeeService;

    // Dependincy Injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public EmployeeDTO getEmployeesbasic() {

        return new EmployeeDTO(12, "Tathagat", LocalDate.of(2024, 5, 15), true);
    }

     // pass the path varibale
    @GetMapping(path="/{id}")
    public EmployeeDTO getEmployees(@PathVariable("id") int employeeid) {

        return new EmployeeDTO(employeeid, "Tathagat", LocalDate.of(2024, 5, 15), true);
    }

    // check the path
    //http://localhost:8082/employee/pathParam?sortBy=age
    @GetMapping(path="/pathParam")
    public String getEmployeesPathParam(@PathParam("sortBy") String sortBy) {
        return "Hello "+ sortBy ;
    }

    @GetMapping(path="/service/{id}")
    public EmployeeDTO getEmployeesById(@PathVariable("id") int employeeid) {

        return employeeService.getEmployeeById(employeeid);
    }

    @GetMapping(path = "/getall")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployee();
    }

    @PostMapping(path="/postcall")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createNewEmployee(employeeDTO);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteEmployeeById(@PathVariable int id) {
        return employeeService.deleteEmployeeById(id);
    }


}

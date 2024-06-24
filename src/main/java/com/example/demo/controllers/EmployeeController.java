package com.example.demo.controllers;

// Creating API using MVC structure
/*-Presentation == controller package folder + DTO package
-Service == service package folder
-persistence == Repositories(Interface) package folder + Entities packages */
//CRUD operations
//GET / employee
//POST /employee
//DELETE / employee /{id}

import com.example.demo.dto.EmployeeDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    @GetMapping(path="/employee") 
    public EmployeeDTO getEmployeesbasic() {

        return new EmployeeDTO(12, "Tathagat", LocalDate.of(2024, 5, 15), true);
    }

     // pass the path varibale
    @GetMapping(path="/employee/{id}")
    public EmployeeDTO getEmployees(@PathVariable("id") int employeeid) {

        return new EmployeeDTO(employeeid, "Tathagat", LocalDate.of(2024, 5, 15), true);
    }

    // check the path
    //http://localhost:8082/employee/pathParam?sortBy=age
    @GetMapping(path="/employee/pathParam")
    public String getEmployeesPathParam(@PathParam("sortBy") String sortBy) {
        return "Hello "+ sortBy ;
    }


}

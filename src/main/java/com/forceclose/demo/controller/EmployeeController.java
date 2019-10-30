package com.forceclose.demo.controller;

import com.forceclose.demo.model.Employee;
import com.forceclose.demo.model.LoginRequest;
import com.forceclose.demo.model.LoginResponse;
import com.forceclose.demo.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employees")
    public HttpEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.findAll();
        if (employees == null || employees.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employees/login")
    public HttpEntity<LoginRequest> loginEmployee(@RequestBody LoginResponse employee) {
        try {
            Employee employee_ = employeeService.login(employee);
            if(employee_!=null){
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setMessage("Usuario Autenticado");
                loginRequest.setFlag(true);
                return new ResponseEntity<>(loginRequest, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/employee/{id}")
    public HttpEntity<Employee> findById(@PathVariable long id) {
        try {
            Employee employees = employeeService.findById(id);
            return new ResponseEntity<>(employees, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/employee")
    public HttpEntity<Employee> register(@RequestBody Employee employee) {
        try {
            Employee employee_Registered = employeeService.register(employee);
            return new ResponseEntity<>(employee_Registered, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employee/{id}")
    public HttpEntity<Employee> update(@PathVariable final String id, @RequestBody Employee employee) {
        Employee employee_Updated = employeeService.update(Long.parseLong(id), employee);
        return (employee_Updated != null) ? new ResponseEntity<>(employee_Updated, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/employee/{id}")
    public HttpEntity<Employee> delete(@PathVariable long id) {
        return (employeeService.deleteById(id)) ? new ResponseEntity<>(null, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}

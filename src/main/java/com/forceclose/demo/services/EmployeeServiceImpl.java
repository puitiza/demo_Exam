package com.forceclose.demo.services;

import com.forceclose.demo.controller.EmployeeController;
import com.forceclose.demo.model.Employee;
import com.forceclose.demo.model.LoginResponse;
import com.forceclose.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee register(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public Employee login(LoginResponse employee) {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee simple :employees) {
            if(simple.getName().equals(employee.getName()) &&
             simple.getPassword().equals(employee.getPassword()) ){
                return simple;
            }
        }
        return null;
    }

    @Override
    public Employee update(long id, Employee employee) {
        try {
            Employee employeeFound = findById(id);
            employeeFound.setId(id);
            employeeFound.setName(employee.getName());
            return employeeRepository.save(employeeFound);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(long id) {
        try {
            Employee employeeFound = findById(id);
            employeeRepository.delete(employeeFound);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}


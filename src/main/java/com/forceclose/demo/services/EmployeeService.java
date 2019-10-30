package com.forceclose.demo.services;

import com.forceclose.demo.model.Employee;
import com.forceclose.demo.model.LoginResponse;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee register(Employee employee);

    Employee findById(long id);

    Employee login(LoginResponse employee);

    Employee update(long id, Employee employee);

    boolean deleteById(long id);
}

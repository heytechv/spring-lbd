package com.fisproject.springlbd.service.employee;

import com.fisproject.springlbd.entity.employee.Employee;

import java.util.HashMap;
import java.util.Map;

public interface EmployeeService {
    Map<Long, Employee> dbMap = new HashMap<>();

    void findAll();
    String getEmployeeNickname(String firstName, String lastName);

    Map<Long, Employee> findByName(String nameOrLastName);
    void save(Employee employee);

}

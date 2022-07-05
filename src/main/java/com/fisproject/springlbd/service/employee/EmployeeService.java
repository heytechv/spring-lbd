package com.fisproject.springlbd.service.employee;

import com.fisproject.springlbd.entity.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmployeeService {
    public abstract void findAll();
    public abstract String getEmployeeNickname(String firstName, String lastName);

    public abstract Map<Long, Employee> findByName(String nameOrLastName);
    public abstract void save(Employee employee);

}

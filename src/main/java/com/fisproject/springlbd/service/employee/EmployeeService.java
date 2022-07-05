package com.fisproject.springlbd.service.employee;

import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    public abstract void findAll();
    public abstract String getEmployeeNickname(String firstName, String lastName);
}

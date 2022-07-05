package com.fisproject.springlbd.service.employee;

import com.fisproject.springlbd.entity.employee.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//@Service("eimpl2")

@Service
@Profile("prod")
public class EmployeeServiceImp2 implements EmployeeService {

    @Override public void findAll() { }
    @Override public String getEmployeeNickname(String firstName, String lastName) { return ""; }
    @Override public Map<Long, Employee> findByName(String nameOrLastName) { return new HashMap<>();  }

    @Override public void save(Employee employee) { }
}

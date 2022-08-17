package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Employee;
import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.repository.ClientRepository;
import com.fisproject.springlbd.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl {

    private EmployeeRepository employeeRepository;


    /** Private
     * */
    private Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }

    private void save(Employee employee) {
        if (employee == null)
            throw new RuntimeException("Employee cannot be null!");
        employeeRepository.save(employee);
    }


    /** Public
     * */
    // todo test
    public void add(Employee employee) {
        save(employee);
    }

    public Employee getById(Long id) {
        return findById(id);
    }


}

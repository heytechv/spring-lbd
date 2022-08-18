package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Employee;
import com.fisproject.springlbd.entity.EmployeeWithRole;
import com.fisproject.springlbd.repository.EmployeeRepository;
import com.fisproject.springlbd.repository.EmployeeWithRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class EmployeeWithRoleServiceImpl {

    private EmployeeWithRoleRepository employeeWithRoleRepository;


    /** Private
     * */
    private EmployeeWithRole findById(Long id) {
        return employeeWithRoleRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }

    private void save(EmployeeWithRole employeeWithRole) {
        if (employeeWithRole == null)
            throw new RuntimeException("EmployeeWithRole cannot be null!");
        employeeWithRoleRepository.save(employeeWithRole);
    }


    /** Public
     * */
//    @Transactional
//    public void addEmployee(Long id, Employee employee) {
//        EmployeeWithRole employeeWithRole = findById(id);
//        employeeWithRole.setEmployee(employee);
//    }

    public EmployeeWithRole getById(Long id) {
        return findById(id);
    }


}

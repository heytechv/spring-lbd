package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.*;
import com.fisproject.springlbd.entity.enums.EmployeeRole;
import com.fisproject.springlbd.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TeamServiceImpl {

    private TeamRepository teamRepository;


    private Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }


    @Transactional
    public void addEmployee(Long id, Employee employee, EmployeeRole role) {

        Team team = findById(id);

        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
        employeeWithRole.setEmployee(employee);
        employeeWithRole.setEmployeeRole(role);
        team.addEmployeeWithRole(employeeWithRole);


        teamRepository.save(team);
    }





}

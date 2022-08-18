package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.*;
import com.fisproject.springlbd.entity.enums.EmployeeRole;
import com.fisproject.springlbd.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamServiceImpl {

    private TeamRepository teamRepository;

    /** Private
     * */
    private Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }

    private void save(Team team) {
        if (team == null)
            throw new RuntimeException("Client cannot be null!");
        teamRepository.save(team);
    }


    /** Public
     * */
    public Team getById(Long id) {
        return findById(id);
    }

    /** Add Employee to Team by id */
    @Transactional
    public void addEmployee(Long id, Employee employee, EmployeeRole employeeRole) {
        Team team = findById(id);

        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
        employeeWithRole.setEmployeeRole(employeeRole);
        employeeWithRole.setTeam(team);
        employeeWithRole.setEmployee(employee);

        teamRepository.save(team);
    }


}

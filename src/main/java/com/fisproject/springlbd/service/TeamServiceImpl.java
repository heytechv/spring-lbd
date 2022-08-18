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

//    public List<EmployeeWithRole> getEmployeeWithRoleList(Long id) {
//        return findById(id).getEmployeeWithRoleList();
//    }


}

package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.Team;
import com.fisproject.springlbd.repository.ClientRepository;
import com.fisproject.springlbd.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl {

    private ProjectRepository projectRepository;

    /** Private
     * */
    private Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }



    /** Public
     * */
    public Project getById(Long id) {
        return findById(id);
    }

    public Team getTeam(Long id) {
        return findById(id).getTeam();
    }

    /** Add Team to Project by id */
    public void addTeam(Long id, Team team) {
        Project project = findById(id);
        project.setTeam(team);
        projectRepository.save(project);
    }

    /** Add Sprint to Project by id */
    public void addSprint(Long id, Sprint sprint) {
        Project project = findById(id);
        project.addSprint(sprint);
        projectRepository.save(project);
    }


}

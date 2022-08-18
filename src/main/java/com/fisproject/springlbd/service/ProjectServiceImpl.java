package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
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

    public void save(Project project) {
        if (project == null)
            throw new RuntimeException("Project cannot be null!");
        projectRepository.save(project);
    }

}

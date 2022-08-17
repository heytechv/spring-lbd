package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.repository.ClientRepository;
import com.fisproject.springlbd.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectServiceImpl {

    private ProjectRepository projectRepository;


    public void add(Project project) {
        projectRepository.save(project);
    }





}

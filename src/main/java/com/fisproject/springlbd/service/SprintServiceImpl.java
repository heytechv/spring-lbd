package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired private SprintRepository sprintRepository;

    @Override public void addSprint() {
        Sprint sprint = new Sprint();
        sprint.setName("zjavy");
        sprint.setStatus("PENDING");
        sprintRepository.save(sprint);
    }

}

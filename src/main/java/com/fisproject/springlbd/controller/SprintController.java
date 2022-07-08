package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SprintController {

    @Autowired SprintService sprintService;

    @GetMapping("/sprints")
    public List<Sprint> getSprintList(@RequestParam("tasks") boolean tasks) {

        return null;

    }
}

package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SprintController {

    @Autowired SprintService sprintService;
    @Autowired UserStoryService userStoryService;

    @GetMapping("/sprints")
    public List<SprintDto> getSprintList(@RequestParam("tasks") boolean showUserStories) {

        List<Sprint> sprints = sprintService.getAllSprintList();

        return sprints.stream().map(sprint -> {
            SprintDto sprintDto = sprintService.convertEntityToDto(sprint);
            if (showUserStories)
                sprintDto.setUserStoryDtos(
                        sprint.getUserStories().stream().map(userStory -> userStoryService.convertEntityToDto(userStory)).collect(Collectors.toList())
                );
            return sprintDto;
        }).collect(Collectors.toList());

//        return sprints;

    }
}

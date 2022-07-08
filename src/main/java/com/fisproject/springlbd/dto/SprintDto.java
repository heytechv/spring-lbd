package com.fisproject.springlbd.dto;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SprintDto {

    private Long id;
    private String name, description;
    private List<UserStoryDto> userStoryDtos;

    public SprintDto(Long id, String name, String description) {
        this.id=id;
        this.name=name;
        this.description=description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<UserStoryDto> getUserStoryDtos() { return userStoryDtos; }
    public void setUserStoryDtos(List<UserStoryDto> userStoryDtos) { this.userStoryDtos = userStoryDtos; }


}

package com.fisproject.springlbd.dto;

import com.fisproject.springlbd.entity.Sprint;

import java.util.List;


public class SprintDto {

    private Long id;
    private String name, description;
    private List<UserStoryZad2Dto> userStoryZad2Dtos;
    private Sprint.StatusType status;

    public SprintDto(Long id, String name, String description, Sprint.StatusType status) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.status=status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<UserStoryZad2Dto> getUserStoryDtos() { return userStoryZad2Dtos; }
    public void setUserStoryDtos(List<UserStoryZad2Dto> userStoryZad2Dtos) { this.userStoryZad2Dtos = userStoryZad2Dtos; }

    public Sprint.StatusType getStatus() { return status; }
    public void setStatus(Sprint.StatusType status) { this.status = status; }

}

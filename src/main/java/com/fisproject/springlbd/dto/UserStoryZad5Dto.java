package com.fisproject.springlbd.dto;

import com.fisproject.springlbd.entity.UserStory;

public class UserStoryZad5Dto {

    private Long id;
    private String name;
    private Integer story_points_amount;
    private UserStory.StatusType status;

    public UserStoryZad5Dto(Long id, String name, Integer story_points_amount, UserStory.StatusType status) {
        this.id=id;
        this.name=name;
        this.story_points_amount=story_points_amount;
        this.status=status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getStoryPointsAmount() { return story_points_amount; }
    public void setStoryPointsAmount(Integer story_points_amount) {
        this.story_points_amount = story_points_amount;
    }

    public UserStory.StatusType getStatus() { return status; }
    public void setStatus(UserStory.StatusType status) { this.status = status; }
}

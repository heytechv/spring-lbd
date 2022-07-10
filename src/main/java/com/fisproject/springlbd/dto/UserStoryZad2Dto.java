package com.fisproject.springlbd.dto;

public class UserStoryZad2Dto {

    private String name;
    private Integer story_points_amount;

    public UserStoryZad2Dto(String name, Integer story_points_amount) {
        this.name=name;
        this.story_points_amount=story_points_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStoryPointsAmount() {
        return story_points_amount;
    }

    public void setStoryPointsAmount(Integer story_points_amount) {
        this.story_points_amount = story_points_amount;
    }
}

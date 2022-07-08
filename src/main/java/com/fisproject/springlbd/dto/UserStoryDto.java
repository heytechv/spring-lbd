package com.fisproject.springlbd.dto;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserStoryDto {


    private String name;
    private Integer story_points_amount;

    public UserStoryDto(String name, Integer story_points_amount) {
        this.name=name;
        this.story_points_amount=story_points_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStory_points_amount() {
        return story_points_amount;
    }

    public void setStory_points_amount(Integer story_points_amount) {
        this.story_points_amount = story_points_amount;
    }
}

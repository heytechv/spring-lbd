package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)  // nie pokazuj pol, ktore nie sa ustawione (sa null)
public class SprintUltimateDto {

    private Long id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private Sprint.StatusType status;
    private List<UserStoryUltimateDto> userStories;


    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setStartDate(Timestamp start_date) { this.startDate = start_date; }
    public Timestamp getStartDate() { return startDate; }

    public void setEndDate(Timestamp end_date) { this.endDate = end_date; }
    public Timestamp getEndDate() { return endDate; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setStatus(Sprint.StatusType status) { this.status = status; }
    public Sprint.StatusType getStatus() { return status; }

    public List<UserStoryUltimateDto> getUserStories() { return userStories; }
    public void setUserStories(List<UserStoryUltimateDto> userStories) { this.userStories = userStories; }


}

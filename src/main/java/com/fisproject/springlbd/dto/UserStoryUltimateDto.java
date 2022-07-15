package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)  // nie pokazuj pol, ktore nie sa ustawione (sa null)
public class UserStoryUltimateDto {


    private Long id;
    private String name;
    private String description;
    private Integer story_points_amount;
    private UserStory.StatusType status;
    private List<AttachmentDto> attachments;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setStoryPointsAmount(Integer story_points_amount) { this.story_points_amount = story_points_amount; }
    public Integer getStoryPointsAmount() { return story_points_amount; }

    public void setStatus(UserStory.StatusType status) { this.status = status; }
    public UserStory.StatusType getStatus() { return status; }

    public List<AttachmentDto> getAttachments() { return attachments; }
    public void setAttachment(List<AttachmentDto> attachmentDtoList) { this.attachments = attachmentDtoList; }



}

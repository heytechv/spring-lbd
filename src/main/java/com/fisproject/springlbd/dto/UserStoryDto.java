package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fisproject.springlbd.entity.UserStory;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)  // nie pokazuj pol, ktore nie sa ustawione (sa null)
@Setter @Getter
public class UserStoryDto {

    @NotNull private String name;
    @NotNull private String description;
    private Integer storyPointsAmount;
    @NotNull private UserStory.StatusType status;
    private List<AttachmentDto> attachments;

}

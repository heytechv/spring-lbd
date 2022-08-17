package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.UserStory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

//@JsonInclude(JsonInclude.Include.NON_NULL)  // nie pokazuj pol, ktore nie sa ustawione (sa null)
@Setter @Getter
@Builder
public class UserStoryDto {

    private Long id;
    @NotNull private String name;
    @NotEmpty private String description;
    private Integer storyPointsAmount;
    @NotNull private UserStory.StatusType status;
    private Set<AttachmentDto> attachmentDtoSet;

}

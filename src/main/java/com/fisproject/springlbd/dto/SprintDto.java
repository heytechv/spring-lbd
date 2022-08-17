package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fisproject.springlbd.entity.Sprint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


@Setter @Getter
@Builder
public class SprintDto {

    private Long id;
    @NotNull private String name;
    @NotNull private Timestamp startDate;
    @NotNull private Timestamp endDate;
    private String description;
    @NotNull private Sprint.StatusType status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) private List<UserStoryDto> userStoryList;  // hide if empty

}

package com.fisproject.springlbd.service;


import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface SprintService {

    void add(@Valid SprintDto sprintDto);
    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;
    UserStory addUserStory(Long id, @Valid UserStoryDto userStoryDto);

    void updateSprintStatus(Long sprintId, Sprint.StatusType newStatus);

    List<SprintDto>      getAll(boolean showUserStories);
    SprintDto            getById(Long id, boolean showUserStories);
    Page<Sprint>         getAllPageAndSortByDate(Integer page, Integer size);
    ArrayList<SprintDto> getBetweenDate(Timestamp start_range, Timestamp end_range);

    List<UserStoryDto> getUserStoryList(Long sprintId);
    List<UserStory> getUserStoryListById(Long id);
    List<UserStory> getUserStoryListByName(String name);
    Integer getStoryPointsById(Long id);
    Integer getStoryPointsAmount(Long sprintId);

}

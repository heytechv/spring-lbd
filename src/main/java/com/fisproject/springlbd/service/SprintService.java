package com.fisproject.springlbd.service;


import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface SprintService {

//    void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException;

    void add(SprintDto sprintDto);

    List<UserStory> getUserStoryListById(Long id);
    List<UserStory> getUserStoryListByName(String name);
    Page<Sprint> findAllPageAndSortByDate(Integer page, Integer size);
    List<Sprint> getAll();
    Integer getStoryPointsById(Long id);

    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;

    List<SprintDto> getSprints(boolean showUserStories);
    Integer getStoryPointsAmount(Long sprintId);

    List<UserStoryDto> getUserStoryList(Long sprintId);
    void updateSprintStatus(Long sprintId, Sprint.StatusType newStatus);
    ArrayList<SprintDto> getBetweenDate(Timestamp start_range, Timestamp end_range);

    void addUserStory(Long id, UserStoryDto userStoryDto);

}

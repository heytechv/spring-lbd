package com.fisproject.springlbd.service;


import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface SprintService {
    void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException;
    List<UserStory> getUserStoryListById(Long id);
    List<UserStory> getUserStoryListByName(String name);
    List<Sprint> getSprintListBetweenDate(Timestamp start_range, Timestamp end_range);
    Integer getStoryPointsById(Long id);
    Page<Sprint> findAllByPageAndSort(Integer page, Integer size);

    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;
}

package com.fisproject.springlbd.service;


import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

public interface SprintService {

    /**
     * Main
     * */
    void save(Sprint sprint);
    void saveAll(List<Sprint> sprintList);
    void add(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException;
    List<Sprint> getSprintListBetweenDate(Timestamp start_range, Timestamp end_range);
    List<Sprint> getAll();

    /**
     * Others
     * */
    List<UserStory> getUserStoryListById(Long id);
    List<UserStory> getUserStoryListByName(String name);
    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;
    Integer getStoryPointsById(Long id);
    Page<Sprint> getAllByPageAndSort(Integer page, Integer size);

}

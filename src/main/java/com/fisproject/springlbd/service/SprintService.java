package com.fisproject.springlbd.service;


import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface SprintService {
    void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException;
    List<UserStory> getUserStoryListById(Long id);
    List<UserStory> getUserStoryListByName(String name);
    List<Sprint> findBetweenDate(Timestamp start_range, Timestamp end_range);
    Page<Sprint> findAllByPageAndSort(Integer page, Integer size);
    List<Sprint> findAll();
    Optional<Sprint> findById(Long id);
    Integer getStoryPointsById(Long id);

    boolean addUserStoryToSprintById(Long id, UserStory userStory, boolean shouldSaveUserStory);

    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;

    void save(Sprint sprint);

    /** Mapper */
    SprintDto convertEntityToDto(Sprint sprint);

}

package com.fisproject.springlbd.service;


import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.SprintUltimateDto;
import com.fisproject.springlbd.dto.SprintZad11Dto;
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
//    List<Sprint> findBetweenDate(Timestamp start_range, Timestamp end_range);
    Page<Sprint> findAllPageAndSortByDate(Integer page, Integer size);
    List<Sprint> findAll();
    Optional<Sprint> findById(Long id);
    Integer getStoryPointsById(Long id);

    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;

    void save(Sprint sprint);

    /** ------------------------------------------------------------------------------------ **
    /** -- Day3 - web responses ------------------------------------------------------------ **
    /** ------------------------------------------------------------------------------------ **/
    StandardResponse getSprints(boolean showUserStories);
    StandardResponse getStoryPointsAmount(Long sprintId);

    StandardResponse getUserStories(Long sprintId);
    StandardResponse updateSprintStatus(Long sprintId, Sprint.StatusType newStatus);
    StandardResponse findBetweenDate(Timestamp start_range, Timestamp end_range);

    StandardResponse addUserStory(Long id, UserStory userStory, boolean shouldSaveUserStory);

    /** ------------------------------------------------------------------------------------ **
    /** -- Mapper -------------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    SprintUltimateDto convertEntityToDto(Sprint sprint);
    SprintUltimateDto convertEntityToZad10Dto(Sprint sprint);

}

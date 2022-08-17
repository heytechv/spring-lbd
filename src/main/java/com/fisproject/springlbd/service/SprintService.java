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

    void add(SprintDto sprintDto);
    void addSprintWithUserStoryZad16(String sprintName) throws IllegalArgumentException;
    UserStory addUserStory(Long id, UserStoryDto userStoryDto);

    void updateSprintStatus(Long sprintId, Sprint.StatusType newStatus);

    List<UserStory> getUserStoryListById(Long id);
    List<UserStory> getUserStoryListByName(String name);
    Page<Sprint> getAllPageAndSortByDate(Integer page, Integer size);
    Integer getStoryPointsById(Long id);
    List<SprintDto> getAll(boolean showUserStories);
    Integer getStoryPointsAmount(Long sprintId);
    List<UserStoryDto> getUserStoryList(Long sprintId);
    ArrayList<SprintDto> getBetweenDate(Timestamp start_range, Timestamp end_range);

}

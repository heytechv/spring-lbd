package com.fisproject.springlbd.service;


import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserStoryService {
    UserStory addUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status) throws IllegalArgumentException;
    Page<UserStory> findAllByPage(Integer page, Integer size);
    List<UserStory> findAll();

    /** Mapper */
    UserStoryDto convertEntityToDto(UserStory userStory);

}

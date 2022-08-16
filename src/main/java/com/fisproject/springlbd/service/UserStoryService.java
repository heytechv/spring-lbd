package com.fisproject.springlbd.service;


import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserStoryService {

    /**
     * Main
     * */
    void save(UserStory userStory);
    void saveAll(List<UserStory> userStoryList);
    UserStory add(String name, String description, Integer story_points_amount, UserStory.StatusType status) throws IllegalArgumentException;
    Page<UserStory> findAllByPage(Integer page, Integer size);
    List<UserStory> getAll();

    /**
     * Others
     * */


}

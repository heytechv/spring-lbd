package com.fisproject.springlbd.service;


import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserStoryService {
    public abstract UserStory addUserStory(String name, String description, Integer story_points_amount, String status) throws SQLException;
    public abstract Page<UserStory> findAllByPage(Integer page, Integer size);
}

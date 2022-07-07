package com.fisproject.springlbd.service;


import java.sql.SQLException;
import java.sql.Timestamp;

public interface UserStoryService {
    public abstract void addUserStory(String name, String description, Integer story_points_amount, String status) throws SQLException;
}

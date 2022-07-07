package com.fisproject.springlbd.service;


import com.fisproject.springlbd.entity.Sprint;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface SprintService {
    public abstract void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, String status) throws SQLException;
    public abstract void getUserStoryListById(Integer id);
}

package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Arrays;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired UserStoryRepository userStoryRepository;

    @Override @Transactional
    public void addUserStory(String name, String description, Integer story_points_amount, String status) throws SQLException {

        if (name == null || name.isEmpty())
            throw new SQLException("[addUserStory] Missing required 'name' field!");
        if (description == null || description.isEmpty())
            throw new SQLException("[addUserStory] Missing required 'description' field!");

        UserStory userStory = new UserStory();
        userStory.setName(name);
        userStory.setDescription(description);
        if (story_points_amount != null) userStory.setStory_points_amount(story_points_amount);

        userStory.setStatus("TO_DO");
        if (status != null && Arrays.asList("TO_DO", "IN_PROGRESS", "REVIEW", "DONE").contains(status))
            userStory.setStatus(status);

        userStoryRepository.save(userStory);
    }
}

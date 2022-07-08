package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired UserStoryRepository userStoryRepository;

    @Override @Transactional
    public UserStory addUserStory(String name, String description, Integer story_points_amount, String status) throws IllegalArgumentException {

        UserStory userStory = new UserStory();
        userStory.setName(name);
        userStory.setDescription(description);
        if (story_points_amount != null) userStory.setStory_points_amount(story_points_amount);

        userStory.setStatus("TO_DO");
        if (status != null && Arrays.asList("TO_DO", "IN_PROGRESS", "REVIEW", "DONE").contains(status))
            userStory.setStatus(status);

        userStoryRepository.save(userStory);

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("[addUserStory] Missing required 'name' field!");
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("[addUserStory] Missing required 'description' field!");

        return userStory;
    }

    @Override @Transactional
    public Page<UserStory> findAllByPage(Integer page, Integer size) {
        return userStoryRepository.findAll(PageRequest.of(page, size));
    }
}

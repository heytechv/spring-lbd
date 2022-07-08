package com.fisproject.springlbd.service;

import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired UserStoryRepository userStoryRepository;

    @Override @Transactional
    public UserStory addUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status) throws IllegalArgumentException {

        UserStory userStory = new UserStory();
        userStory.setName(name);
        userStory.setDescription(description);
        if (story_points_amount != null) userStory.setStory_points_amount(story_points_amount);

        userStory.setStatus(UserStory.StatusType.TO_DO);
        if (status != null && Arrays.asList(UserStory.StatusType.values()).contains(status))
            userStory.setStatus(status);

        userStoryRepository.save(userStory);

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("[addUserStory] Missing required 'name' field!");
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("[addUserStory] Missing required 'description' field!");

        return userStory;
    }

    @Override public Page<UserStory> findAllByPage(Integer page, Integer size) {
        return userStoryRepository.findAll(PageRequest.of(page, size));
    }

    /** Mapper */
    @Override public UserStoryDto convertEntityToDto(UserStory userStory) {
        return new UserStoryDto(userStory.getName(), userStory.getStoryPointsAmount());
    }

}

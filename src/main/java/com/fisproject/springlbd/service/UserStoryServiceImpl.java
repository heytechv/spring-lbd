package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired UserStoryRepository userStoryRepository;


    @Override public void save(UserStory userStory) {
        if (userStory == null)
            throw new RuntimeException("UserStory cannot be null!");
        userStoryRepository.save(userStory);
    }

    @Override @Transactional
    public void saveAll(List<UserStory> userStoryList) {
        if (userStoryList == null)
            throw new RuntimeException("UserStory list cannot be null!");
        userStoryRepository.saveAll(userStoryList);
    }

    @Override @Transactional
    public UserStory add(String name, String description, Integer storyPointsAmount, UserStory.StatusType status) throws IllegalArgumentException {
        UserStory userStory = UserStory.builder()
                .name(name)
                .description(description)
                .build();
        if (storyPointsAmount != null) userStory.setStoryPointsAmount(storyPointsAmount);

        userStory.setStatus(UserStory.StatusType.TO_DO);
        if (status != null && Arrays.asList(UserStory.StatusType.values()).contains(status))
            userStory.setStatus(status);

        userStoryRepository.save(userStory);

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("[addUserStory] Missing required 'name' field!");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("[addUserStory] Missing required 'description' field!");

        return userStory;
    }

    @Override public List<UserStory> getAll() {
        return (List<UserStory>) userStoryRepository.findAll();
    }

    @Override public Page<UserStory> findAllByPage(Integer page, Integer size) {
        return userStoryRepository.findAll(PageRequest.of(page, size));
    }
}

package com.fisproject.springlbd.utils;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import com.fisproject.springlbd.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class CreateRandomUserStories {

    @Autowired UserStoryService userStoryService;


    public void create(int amount) {

        List<UserStory> userStoryList = new ArrayList<>();

        for (int i=0; i<amount; i++) {
            Random random = new Random();

            UserStory us = UserStory.builder()
                    .name(UUID.randomUUID().toString())
                    .description(UUID.randomUUID().toString())
                    .storyPointsAmount(random.nextInt(10))
                    .status(Arrays.asList(UserStory.StatusType.values()).get(random.nextInt(4)).toString())
                    .build();
            userStoryList.add(us);
        }

        userStoryService.saveAll(userStoryList);
    }

}

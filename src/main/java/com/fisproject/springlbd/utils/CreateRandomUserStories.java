package com.fisproject.springlbd.utils;

import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class CreateRandomUserStories {

    @Transactional
    public void create(ApplicationContext context, int amount) {

        UserStoryRepository userStoryRepository = context.getBean(UserStoryRepository.class);

        ArrayList<UserStory> userStories = new ArrayList<>();

        for (int i=0; i<amount; i++) {
            Random random = new Random();
            UserStory us = new UserStory();
            us.setName(UUID.randomUUID().toString());
            us.setDescription(UUID.randomUUID().toString());
            us.setStory_points_amount(random.nextInt(10));
            us.setStatus(Arrays.asList("TO_DO", "IN_PROGRESS", "REVIEW", "DONE").get(random.nextInt(4)));

            userStories.add(us);
        }
        userStoryRepository.saveAll(userStories);
    }

}

package com.fisproject.springlbd.utils;

import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import com.fisproject.springlbd.service.UserStoryService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
@AllArgsConstructor
public class CreateRandomUserStories {

    UserStoryService userStoryService;

    public void create(int amount) {
        List<UserStoryDto> userStoryDtoList = new ArrayList<>();
        Random random = new Random();

        for (int i=0; i<amount; i++) {
            UserStoryDto userStoryDto = UserStoryDto.builder()
                    .name("UserStory (auto) "+i)
                    .description("Autogenerated user story")
                    .storyPointsAmount(random.nextInt(10))
                    .status(Arrays.asList(UserStory.StatusType.values()).get(random.nextInt(4)))
                    .build();
            userStoryDtoList.add(userStoryDto);
        }
        userStoryService.addAll(userStoryDtoList);
    }

}

package com.fisproject.springlbd;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import com.fisproject.springlbd.utils.CreateRandomSprints;
import com.fisproject.springlbd.utils.CreateRandomUserStories;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;



@SpringBootTest
class SpringLbdApplicationTests {

    public static final Logger LOG = LoggerFactory.getLogger(SpringLbdApplicationTests.class);

    @Autowired SprintService sprintService;
    @Autowired UserStoryService userStoryService;
    @Autowired ApplicationContext context;

    /** Zad 8 Test */
    @Test void test_zad8_SprintServiceOK() throws IllegalArgumentException {
        Sprint sprint = Sprint.builder()
                .name("SH")
                .startDate(Timestamp.valueOf("2022-07-06 00:00:00.0"))
                .endDate(Timestamp.valueOf("2022-07-07 00:00:00.0"))
                .description("opis jakis")
                .status(Sprint.StatusType.IN_PROGRESS)
                .build();
        sprintService.add(sprint);
    }

    @Test void test_zad8_UserStoryServiceOK() throws IllegalArgumentException {
        UserStory userStory = UserStory.builder()
                .name("SH")
                .description( "opis jakis")
                .storyPointsAmount(1)
                .status(UserStory.StatusType.TO_DO)
                .build();
        userStoryService.add(userStory);
    }

    @Test void test_zad8_SprintServiceException() {
        boolean isE = false;

        try {
            Sprint sprint = Sprint.builder()
                    .name("SH")
                    .startDate(Timestamp.valueOf("2022-07-06 00:00:00.0"))
                    .endDate(null)
                    .description("opis jakis")
                    .status(Sprint.StatusType.PENDING)
                    .build();
            sprintService.add(sprint);

        } catch (Exception e) {
            LOG.error(e.getMessage());
            isE = true;
        }
        assert isE;
    }

    @Test void test_zad8_UserStoryServiceException() {
        boolean isE = false;

        try {
            UserStory userStory = UserStory.builder()
                    .name("SH")
                    .description("")  // pusty
                    .storyPointsAmount(1)
                    .status(UserStory.StatusType.TO_DO)
                    .build();
            userStoryService.add(userStory);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            isE = true;
        }
        assert isE;
    }

    /** Zad 9 Test */
    @Test void test_zad9_SprintByIdGetUserStories() {
        List<UserStory> userStories = sprintService.getUserStoryListById(1L);
        userStories.forEach(us -> System.out.println(us.getId() + " | " + us.getName() + " | " + us.getStatus()));

        assert userStories.size() > 0;
    }

    /** Zad 10 Test */
    @Test void test_zad10_SprintsBetweenDateRange() {
        List<SprintDto> sprints = sprintService.getBetweenDate(
                        Timestamp.valueOf("2022-07-01 00:00:00.0"),
                        Timestamp.valueOf("2022-07-07 00:00:00.0")
        );
        sprints.forEach(s -> System.out.println(s.getName() + " | " + s.getStartDate() + " | "+ s.getStatus()));

        assert sprints.size() > 0;
    }

    /** Zad 11 Test */
    @Test void test_zad11_StoryPointsById() {
        Long id = 1L;

        Integer storyPoints = sprintService.getStoryPointsById(id);
        System.out.println("Sprint with id = "+id + " has " + storyPoints + " story points.");
    }

    /** Zad 12 Test */
    @Test void test_zad12_createRandom() {
        new CreateRandomUserStories().create(context, 100);
        userStoryService.getAll().forEach(us -> System.out.println(us.getId()+"\t|\t"+us.getName()+"\t|\t"+us.getStatus()));
    }

    /** Zad 14 Test */
    @Test void test_zad13_pagination() {
        new CreateRandomUserStories().create(context, 100);

        Page<UserStory> userStories = userStoryService.getAllByPage(1, 10);

        for (UserStory us : userStories)
            System.out.println(us.getId() + " | " + us.getName() + " | " + us.getStatus());


        assert userStories.getSize() == 10;
    }

    /** Zad 15 Test */
    @Test void test_zad15_paginationSort() {
        new CreateRandomSprints().create(context, 100);

        Page<Sprint> sprints = sprintService.getAllPageAndSortByDate(0, 10);
        sprints.forEach(s -> System.out.println(s.getId() + "\t|\t" + s.getName() + "\t|\t" + s.getStartDate() + "\t|\t" + s.getStatus()));
    }

    /** Zad 16 Test */
    @Test void test_zad16_addSprintWithUserStory() throws IllegalArgumentException {
        String sprintName = "sprint_zad16";
        sprintService.addSprintWithUserStoryZad16(sprintName);

        List<UserStory> userStories = sprintService.getUserStoryListByName(sprintName);
        userStories.forEach(us -> System.out.println(us.getId() + " | " + us.getName() + " | " + us.getStatus()));

        assert userStories.size() > 0;
    }

    /** rob */
    @Test void test_rob() {
        sprintService.addSprintWithUserStoryZad16("roboczy");

        List<SprintDto> sprintDtoList = sprintService.getAll(false);
        sprintDtoList.forEach(sprint -> System.out.println(sprint.getName()));


    }


}

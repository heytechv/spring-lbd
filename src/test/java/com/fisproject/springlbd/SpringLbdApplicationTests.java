package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import com.fisproject.springlbd.utils.CreateRandomUserStories;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class SpringLbdApplicationTests {

    public static final Logger LOG = LoggerFactory.getLogger(SpringLbdApplicationTests.class);

    @Autowired SprintService sprintService;
    @Autowired UserStoryService userStoryService;
    @Autowired ApplicationContext context;

    /** Zad 8 Test */
    @Test void test_zad8_SprintServiceOK() throws SQLException {
        sprintService.addSprint(
                "SH",
                Timestamp.valueOf("2022-07-06 00:00:00.0"),
                Timestamp.valueOf("2022-07-07 00:00:00.0"),
                "opis jakis",
                "PENDING");
    }

    @Test void test_zad8_UserStoryServiceOK() throws SQLException {
        userStoryService.addUserStory(
                "SH",
                "opis jakis",
                1,
                "");
    }

    @Test void test_zad8_SprintServiceException() {
        boolean isE = false;

        try {
            sprintService.addSprint(
                    "SH",
                    Timestamp.valueOf("2022-07-06 00:00:00.0"),
                    Timestamp.valueOf("2022-07-07 00:00:00.0"),
                    "opis jakis",
                    "PENDINGx");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            isE = true;
        }
        assert isE;
    }

    @Test void test_zad8_UserStoryServiceException() {
        boolean isE = false;

        try {
            userStoryService.addUserStory(
                    "",
                    "opis jakis",
                    1,
                    "");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            isE = true;
        }
        assert isE;
    }

    /** Zad 9 Test */
    @Test void test_zad9_SprintByIdGetUserStories() {
        List<UserStory> userStories = sprintService.getUserStoryListById(1L);

        for (UserStory us : userStories)
            System.out.println(us.getName() + " | " + us.getStatus());

        assert userStories.size() > 0;
    }

    /** Zad 10 Test */
    @Test void test_zad10_SprintsBetweenDateRange() {
        List<Sprint> sprints = sprintService
                .getSprintListBetweenDate(
                        Timestamp.valueOf("2022-07-01 00:00:00.0"),
                        Timestamp.valueOf("2022-07-07 00:00:00.0")
                );

        for (Sprint s : sprints)
            System.out.println(s.getName() + " | " + s.getStart_date() + " | "+ s.getStatus());

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
    }
}

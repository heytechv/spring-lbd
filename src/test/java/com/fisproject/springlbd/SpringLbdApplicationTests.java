package com.fisproject.springlbd;

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
    @Autowired CreateRandomSprints createRandomSprints;
    @Autowired CreateRandomUserStories createRandomUserStories;


    /** Zad 8 Test */
    @Test void test_zad8_SprintServiceOK() throws IllegalArgumentException {
        sprintService.add(
                "SH",
                Timestamp.valueOf("2022-07-06 00:00:00.0"),
                Timestamp.valueOf("2022-07-07 00:00:00.0"),
                "opis jakis",
                Sprint.StatusType.IN_PROGRESS);
    }

    @Test void test_zad8_UserStoryServiceOK() throws IllegalArgumentException {
        userStoryService.add(
                "SH",
                "opis jakis",
                1,
                null);
    }

    @Test void test_zad8_SprintServiceException() {
        boolean isE = false;

        try {
            sprintService.add(
                    "SH",
                    Timestamp.valueOf("2022-07-06 00:00:00.0"),
                    null,
                    "",
                    Sprint.StatusType.PENDING);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            isE = true;
        }
        assert isE;
    }

    @Test void test_zad8_UserStoryServiceException() {
        boolean isE = false;

        try {
            userStoryService.add(
                    "jakies",
                    "", // PUSTY!
                    1,
                    null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            isE = true;
        }
        assert isE;
    }

    /** Zad 9 Test */
    @Test void test_zad9_SprintByIdGetUserStories() {
        List<UserStory> userStories = sprintService.getUserStoryListById(1L);
        userStories.forEach(us -> System.out.println(us.getId() + "\t|\t" + us.getName() + "\t|\t" + us.getStatus()));

        assert userStories.size() > 0;
    }

    /** Zad 10 Test */
    @Test void test_zad10_SprintsBetweenDateRange() {
        List<Sprint> sprints = sprintService
                .getSprintListBetweenDate(
                        Timestamp.valueOf("2022-01-01 00:00:00.0"),
                        Timestamp.valueOf("2023-07-07 00:00:00.0")
                );
        sprints.forEach(s -> System.out.println(s.getName() + "\t|\t" + s.getStartDate() + "\t|\t"+ s.getStatus()));

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
        createRandomUserStories.create(100);

        assert userStoryService.getAll().size() >= 100;
    }

    /** Zad 14 Test */
    @Test void test_zad13_pagination() {
        createRandomUserStories.create(100);

        Page<UserStory> userStories = userStoryService.findAllByPage(1, 10);
        userStories.forEach(us -> System.out.println(us.getId() + " | " + us.getName() + " | " + us.getStatus()));

        assert userStories.getSize() == 10;
    }

    /** Zad 15 Test */
    @Test void test_zad15_paginationSort() {
        createRandomSprints.create(100);

        Page<Sprint> sprints = sprintService.getAllByPageAndSort(0, 10);
        System.out.println("id\t|\tname\t|\tstart_date");
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


}



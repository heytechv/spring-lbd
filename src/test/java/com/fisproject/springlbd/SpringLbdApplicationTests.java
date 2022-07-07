package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


@SpringBootTest
class SpringLbdApplicationTests {

    public static final Logger LOG = LoggerFactory.getLogger(SpringLbdApplicationTests.class);

    @Autowired SprintService sprintService;
    @Autowired UserStoryService userStoryService;

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

}

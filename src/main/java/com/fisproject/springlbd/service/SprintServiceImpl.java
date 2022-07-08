package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired private SprintRepository sprintRepository;
    @Autowired private UserStoryRepository userStoryRepository;

    @Override
    public boolean checkArgs(String name, Timestamp start_date, Timestamp end_date, String description, String status) throws SQLException {
        if (name == null || name.isEmpty())
            throw new SQLException("[addSprint] Missing required 'name' field!");
        if (start_date == null || end_date == null || start_date.after(end_date))
            throw new SQLException("[addSprint] Missing required 'start_date', 'end_date' fields!");
        if (start_date.after(end_date))
            throw new SQLException("[addSprint] 'end_date' must be greater than 'start_date'!");
        if (status == null || status.isEmpty())
            throw new SQLException("[addSprint] Missing required 'status' field!");
        if (!Arrays.asList("PENDING","IN_PROGRESS","FINISHED","CANCELED").contains(status))
            throw new SQLException("[addSprint] Status type not found!");

        return true;
    }

    @Override @Transactional                                                                                            // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring - @Transactional pozwala na rollback po jakimkolwiek runtime exception
    public void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, String status) throws SQLException {

        checkArgs(name, start_date, end_date, description, status);

        Sprint sprint = new Sprint();
        sprint.setName(name);
        sprint.setStartDate(start_date);
        sprint.setEndDate(end_date);
        sprint.setStatus(status);
        if (description != null) sprint.setDescription(description);

        sprintRepository.save(sprint);
    }

    @Override @Transactional public List<UserStory> getUserStoryListById(Long id) {
        Optional<Sprint> foundSprint = sprintRepository.findById(id);

//        foundSprint.ifPresent(story -> {
//            List<UserStory> userStories = story.getUserStories();
//            for (UserStory userStory : userStories)
//                System.out.println(userStory.getName());
//        });


        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStories())).orElse(null);    // tutaj new Array bo inaczej LazyException
    }

    @Override public List<UserStory> getUserStoryListByName(String name) {
        Optional<Sprint> foundSprint = sprintRepository.findByName(name);
        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStories())).orElse(null);
    }

    @Override @Transactional public List<Sprint> getSprintListBetweenDate(Timestamp start_range, Timestamp end_range) {
        return sprintRepository.getSprintListBetweenDates(start_range, end_range);
    }

    @Override @Transactional public Integer getStoryPointsById(Long id) {
        Integer points = sprintRepository.getStoryPointsById(id);
        return points != null ? points : 0;
    }

    @Override public Page<Sprint> findAllByPageAndSort(Integer page, Integer size) {
        // https://www.baeldung.com/spring-data-jpa-pagination-sorting
        return sprintRepository.findAll(
                PageRequest.of(page, size,
                        Sort.by("startDate")));

    }

    @Override @Transactional public void addSprintWithUserStoryZad16(String sprintName) {

        UserStory userStory = new UserStory();
        userStory.setName("user_story_zad16");
        userStory.setDescription("opis user story (zad 16)");
        userStory.setStory_points_amount(12);
        userStory.setStatus("IN_PROGRESS");
        userStoryRepository.save(userStory);

        Sprint sprint = new Sprint();
        sprint.setName(sprintName);
        sprint.setStartDate(Timestamp.valueOf("2022-07-07 00:00:00.0"));
        sprint.setEndDate(Timestamp.valueOf("2022-07-08 00:00:00.0"));
        sprint.setDescription("sprint na potrzeby zad 16 :)");
        sprint.setStatus("PENDING");
        sprint.addUserStory(userStory);
        sprintRepository.save(sprint);

    }


}

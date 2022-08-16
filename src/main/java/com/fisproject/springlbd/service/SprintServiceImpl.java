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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired private SprintRepository sprintRepository;
    @Autowired private UserStoryRepository userStoryRepository;


    /**
     * Main
     * */
    // @Transactional needs UNCHECKED exception for rollback!
    @Override public void save(Sprint sprint) {
        if (sprint == null)
            throw new RuntimeException("Sprint cannot be null!");
        sprintRepository.save(sprint);
    }

    @Override @Transactional
    public void saveAll(List<Sprint> sprintList) {
        if (sprintList == null)
            throw new RuntimeException("Sprint list cannot be null!");
        sprintRepository.saveAll(sprintList);
    }

    @Override @Transactional                                                                                            // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring - @Transactional pozwala na rollback po jakimkolwiek runtime exception
    public void add(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException {
        Sprint sprint = Sprint.builder()
                .name(name)
                .startDate(start_date)
                .endDate(end_date)
                .status(status.toString())
                .build();
        if (description != null) sprint.setDescription(description);

        sprintRepository.save(sprint);

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("[addSprint] Missing required 'name' field!");
        if (start_date == null || end_date == null)
            throw new IllegalArgumentException("[addSprint] Missing required 'start_date', 'end_date' fields!");
        if (start_date.after(end_date))
            throw new IllegalArgumentException("[addSprint] 'end_date' must be greater than 'start_date'!");
        if (!Arrays.asList(Sprint.StatusType.values()).contains(status))
            throw new IllegalArgumentException("[addSprint] Status type not found!");
    }

    @Override public List<Sprint> getAll() {
        return (List<Sprint>) sprintRepository.findAll();
    }

    @Override public Page<Sprint> getAllByPageAndSort(Integer page, Integer size) {
        // https://www.baeldung.com/spring-data-jpa-pagination-sorting
        return sprintRepository.findAll(
                PageRequest.of(page, size,
                        Sort.by("startDate")));
    }

    /**
     * Other
     * */
    @Override public List<Sprint> getSprintListBetweenDate(Timestamp start_range, Timestamp end_range) {
        return sprintRepository.getSprintListBetweenDates(start_range, end_range);
    }

    @Override public Integer getStoryPointsById(Long id) {
        // Sprint->UserStories->Story points sum (only if UserStory status=DONE)
        Integer points = sprintRepository.getStoryPointsById(id);
        return points != null ? points : 0;
    }

    @Override public List<UserStory> getUserStoryListById(Long id) {
        Optional<Sprint> foundSprint = sprintRepository.findById(id);
        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStoryList())).orElse(null);                    // tutaj new Array bo inaczej LazyException
    }

    @Override public List<UserStory> getUserStoryListByName(String name) {
        Optional<Sprint> foundSprint = sprintRepository.findByName(name);
        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStoryList())).orElse(null);
    }

    @Override @Transactional public void addSprintWithUserStoryZad16(String sprintName) {
        UserStory userStory = UserStory.builder()
                .name("user_story_zad16")
                .description("opis user story (zad 16)")
                .storyPointsAmount(12)
                .status(UserStory.StatusType.IN_PROGRESS.toString())
                .build();
        userStoryRepository.save(userStory);

        Sprint sprint = Sprint.builder()
                .name(sprintName)
                .startDate(Timestamp.valueOf("2022-07-07 00:00:00.0"))
                .endDate(Timestamp.valueOf("2022-07-08 00:00:00.0"))
                .description("sprint na potrzeby zad 16 :)")
                .status(Sprint.StatusType.PENDING.toString())
                .build();
        sprint.addUserStory(userStory);
        sprintRepository.save(sprint);
    }


}

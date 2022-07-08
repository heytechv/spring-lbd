package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override @Transactional                                                                                            // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring - @Transactional pozwala na rollback po jakimkolwiek runtime exception
    public void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, String status) throws SQLException {

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

        Sprint sprint = new Sprint();
        sprint.setName(name);
        sprint.setStart_date(start_date);
        sprint.setEnd_date(end_date);
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

    @Override @Transactional public List<Sprint> getSprintListBetweenDate(Timestamp start_range, Timestamp end_range) {
        return sprintRepository.getSprintListBetweenDates(start_range, end_range);
    }

    @Override @Transactional public Integer getStoryPointsById(Long id) {
        Integer points = sprintRepository.getStoryPointsById(id);
        return points != null ? points : 0;
    }


}

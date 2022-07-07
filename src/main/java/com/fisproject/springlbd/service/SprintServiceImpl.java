package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public void getUserStoryListById(Integer id) {

    }


}

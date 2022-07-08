package com.fisproject.springlbd.utils;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.repository.SprintRepository;
import org.springframework.context.ApplicationContext;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class CreateRandomSprints {

    @Transactional
    public void create(ApplicationContext context, int amount) {

        SprintRepository sprintRepository = context.getBean(SprintRepository.class);

        ArrayList<Sprint> sprints = new ArrayList<>();

        for (int i=0; i<amount; i++) {
            Random random = new Random();
            int rndYear = random.nextInt(8)+1;
            int rndDay = random.nextInt(7)+1;

            Sprint s = new Sprint();
            s.setName(UUID.randomUUID().toString());
            s.setDescription(UUID.randomUUID().toString());
            s.setStartDate(Timestamp.valueOf("202"+rndYear+"-0"+rndDay     +"-01 00:00:00.0"));
            s.setEndDate(Timestamp.valueOf("202"+rndYear+"-0"+(rndDay+1) +"-01 05:00:00.0"));
            s.setStatus(Arrays.asList("PENDING","IN_PROGRESS","FINISHED","CANCELED").get(random.nextInt(4)));

            sprints.add(s);
        }
        sprintRepository.saveAll(sprints);
    }

}

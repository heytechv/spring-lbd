package com.fisproject.springlbd.utils;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

@Component
public class CreateRandomSprints {

    @Autowired private SprintService sprintService;


    public void create(int amount) {

        List<Sprint> sprintList = new ArrayList<>();

        for (int i=0; i<amount; i++) {
            Random random = new Random();
            int rndYear  = random.nextInt(8) + 1;
            int rndMonth = random.nextInt(8) + 1;

            Sprint s = Sprint.builder()
                    .name(UUID.randomUUID().toString())
                    .description(UUID.randomUUID().toString())
                    .startDate(Timestamp.valueOf("202"+rndYear+"-0"+rndMonth +"-01 00:00:00.0"))
                    .endDate  (Timestamp.valueOf("202"+rndYear+"-0"+rndMonth +"-02 05:00:00.0"))
                    .status(Arrays.asList(Sprint.StatusType.values()).get(random.nextInt(4)).toString())
                    .build();
            sprintList.add(s);
        }

        sprintService.saveAll(sprintList);
    }

}

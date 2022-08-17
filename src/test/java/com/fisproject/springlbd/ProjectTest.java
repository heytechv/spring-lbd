package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.entity.Team;
import com.fisproject.springlbd.entity.enums.TeamType;
import com.fisproject.springlbd.repository.ProjectRepository;
import com.fisproject.springlbd.service.ClientServiceImpl;
import com.fisproject.springlbd.service.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectTest {

    @Autowired ClientServiceImpl clientService;
    @Autowired ProjectServiceImpl projectService;
    @Autowired ProjectRepository projectRepository;

    private Logger log = LoggerFactory.getLogger(ProjectTest.class);

    @Test
    void mainTest() {
        Client client = new Client();
        client.setCity("Warszawa");
        client.setCountryCode("PL");

        Project project = new Project();
        project.setTitle("Projekt stazowy");
//        projectRepository.save(project);

        Team team = new Team();
        team.setTeamType(TeamType.JAVA);
        project.setTeam(team);

        client.addProject(project);
        clientService.addNew(client);








        log.info("SIEMA");


    }

}

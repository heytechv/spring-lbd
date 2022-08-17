package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.*;
import com.fisproject.springlbd.entity.enums.EmployeeRole;
import com.fisproject.springlbd.entity.enums.TeamType;
import com.fisproject.springlbd.repository.ProjectRepository;
import com.fisproject.springlbd.service.ClientServiceImpl;
import com.fisproject.springlbd.service.ProjectServiceImpl;
import com.fisproject.springlbd.service.TeamServiceImpl;
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
    @Autowired TeamServiceImpl teamService;

    private Logger log = LoggerFactory.getLogger(ProjectTest.class);

    @Test
    void mainTest() {
        Client client = new Client();
        client.setCity("Warszawa");
        client.setCountryCode("PL");

        Project project = new Project();
        project.setTitle("Projekt stazowy");

        Team team = new Team();
        team.setTeamType(TeamType.JAVA);

        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
        employeeWithRole.setEmployeeRole(EmployeeRole.DESIGNER);

        Employee employee = new Employee();
        employee.setFirstName("Maciek");


        client.addProject(project);
        project.setTeam(team);
        team.addEmployeeWithRole(employeeWithRole);
        employeeWithRole.setEmployee(employee);


        clientService.add(client);


//        teamService.addEmployee(1L, employee, EmployeeRole.DESIGNER);








        log.info("SIEMA");


    }

}

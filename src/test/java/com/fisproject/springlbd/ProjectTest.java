package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.*;
import com.fisproject.springlbd.entity.enums.EmployeeRole;
import com.fisproject.springlbd.entity.enums.TeamType;
import com.fisproject.springlbd.repository.ProjectRepository;
import com.fisproject.springlbd.service.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectTest {

    @Autowired ClientServiceImpl clientService;
    @Autowired ProjectServiceImpl projectService;
    @Autowired TeamServiceImpl teamService;
    @Autowired EmployeeServiceImpl employeeService;
    @Autowired EmployeeWithRoleServiceImpl employeeWithRoleService;

    private Logger log = LoggerFactory.getLogger(ProjectTest.class);


    @Test void mainTest() {
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

        // ------------------------------
        client.addProject(project);
        project.setTeam(team);
        team.addEmployeeWithRole(employeeWithRole);
        employeeWithRole.setEmployee(employee);

        // ------------------------------
        clientService.add(client);
    }

    @Test void mainTest2() {
        Employee employee = new Employee();
        employee.setFirstName("Maciek");
        employeeService.add(employee);

        // ------------------------------
        Client client = new Client();
        client.setCity("Warszawa");
        client.setCountryCode("PL");

        Project project = new Project();
        project.setTitle("Projekt stazowy");

        Team team = new Team();
        team.setTeamType(TeamType.JAVA);

        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
        employeeWithRole.setEmployeeRole(EmployeeRole.DESIGNER);

        // ------------------------------
        client.addProject(project);
        project.setTeam(team);
        team.addEmployeeWithRole(employeeWithRole);
        employeeWithRole.setEmployee(employeeService.getById(1L));

        clientService.add(client);

//        employeeWithRoleService.addEmployee(1L, employee);

    }

}

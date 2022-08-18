package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.*;
import com.fisproject.springlbd.entity.enums.EmployeeRole;
import com.fisproject.springlbd.entity.enums.TeamType;
import com.fisproject.springlbd.repository.*;
import com.fisproject.springlbd.service.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class ProjectTest {

    @Autowired ClientServiceImpl clientService;
    @Autowired ProjectServiceImpl projectService;
    @Autowired TeamServiceImpl teamService;
    @Autowired EmployeeServiceImpl employeeService;
    @Autowired EmployeeWithRoleServiceImpl employeeWithRoleService;

    @Autowired ClientRepository clientRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired EmployeeRepository employeeRepository;
    @Autowired EmployeeWithRoleRepository employeeWithRoleRepository;

    private Logger log = LoggerFactory.getLogger(ProjectTest.class);


/*    @Test void mainTest() {
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
        clientService.save(client);
    }

    @Test void mainTest2() {
        Employee employee = new Employee();
        employee.setFirstName("Maciek");
        employeeService.add(employee);

        // ------------------------------
        Client client = new Client();
        client.setCity("Warszawa");
        client.setCountryCode("PL");
        clientRepository.save(client);

        Project project = new Project();
        project.setTitle("Projekt stazowy");
        projectRepository.save(project);

        if (true) return;

        Team team = new Team();
        team.setTeamType(TeamType.JAVA);

        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
        employeeWithRole.setEmployeeRole(EmployeeRole.DESIGNER);

        // ------------------------------
        client.addProject(project);
        project.setTeam(team);
        team.addEmployeeWithRole(employeeWithRole);
        employeeWithRole.setEmployee(employeeService.getById(1L));

        // ------------------------------
        clientService.save(client);


        // ============== 2 ==============
        Project project2 = new Project();
        project2.setTitle("Projekt stazowy 2");

        Team team2 = new Team();
        team2.setTeamType(TeamType.NET);

        EmployeeWithRole employeeWithRole2 = new EmployeeWithRole();
        employeeWithRole2.setEmployeeRole(EmployeeRole.ANALYST);

        // ------------------------------
        client.addProject(project2);
        project2.setTeam(team2);
        team2.addEmployeeWithRole(employeeWithRole2);
        employeeWithRole2.setEmployee(employeeService.getById(1L));

        // ------------------------------
        clientService.save(client);


    }*/


    @Test void test() {
        /** Client -> addClient */
        Client client = new Client();
        client.setCity("Warszawa");
        client.setCountryCode("PL");

        clientRepository.save(client);

        /** Client -> addProjectToClientById / Project -> addProjectToClientById (idk jak lepiej) */
        Project project = new Project();
        project.setTitle("Projekt stazowy");

        Client clientOK = clientService.getById(1L);
        clientOK.addProject(project);
//        projectRepository.save(project);  // obydwa dzialaja (dziala w dwie strony)
        clientRepository.save(clientOK);  // obydwa dzialaja (dziala w dwie strony)

        /** Project -> addTeamToProjectById */
        Team team = new Team();
        team.setTeamType(TeamType.JAVA);

        Project projectOK = projectService.getById(1L);
        projectOK.setTeam(team);
//        teamRepository.save(team);  // nie dziala (nie zapisuje team_id)
        projectRepository.save(projectOK);  // dziala

        /** OSOBNO (Niezaleznie od reszty): Employee -> addEmployee */
        Employee employee = new Employee();
        employee.setFirstName("Maciek");
        employeeRepository.save(employee);

        /** Team -> addEmployeeWithRoleToTeamById */
        Team teamOK = teamService.getById(1L);
        Employee employeeOK = employeeService.getById(1L);

        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
        employeeWithRole.setEmployeeRole(EmployeeRole.DESIGNER);
        employeeWithRole.setTeam(teamOK);
        employeeWithRole.setEmployee(employeeOK);
        employeeWithRoleRepository.save(employeeWithRole);

        System.out.println("");
//
//        teamOK.getEmployeeWithRoleSet().add(employeeWithRole);
//        employeeOK.getEmployeeWithRoleSet().add(employeeWithRole);

//        teamRepository.save(teamOK);
//        employeeRepository.save(employeeOK);


//        employeeRepository.save(employeeOK);

//        employeeWithRole.setEmployee(employeeOK);
//        employeeWithRoleRepository.save(employeeWithRole);




//
//
//        Team team = new Team();
//        team.setTeamType(TeamType.JAVA);
//
//        project.setTeam(team);
////        clientRepository.save(client);
////        projectRepository.save(project);
//
//
//        EmployeeWithRole employeeWithRole = new EmployeeWithRole();
//        employeeWithRole.setEmployeeRole(EmployeeRole.DESIGNER);
//
//        team.addEmployeeWithRole(employeeWithRole);
//        clientRepository.save(client);




    }

}

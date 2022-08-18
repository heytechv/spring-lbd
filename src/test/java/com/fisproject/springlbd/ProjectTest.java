package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.*;
import com.fisproject.springlbd.entity.enums.CityDepartment;
import com.fisproject.springlbd.entity.enums.EmployeeContract;
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

    @Test void testFirst() {
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
        teamRepository.save(teamOK);  // dziala
//        employeeWithRoleRepository.save(employeeWithRole);  // dziala

    }

    @Test void testService() {
        Employee employee = new Employee();
        employee.setFirstName("Maciek");
        employee.setLastName("Wyszkowski");
        employee.setContract(EmployeeContract.B2B);
        employee.setCityDepartment(CityDepartment.GLIWICE);
        employeeService.add(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Patryk");
        employee2.setLastName("Wojak");
        employee2.setContract(EmployeeContract.ETAT);
        employee2.setCityDepartment(CityDepartment.GLIWICE);
        employeeService.add(employee2);

        // --

        Client client = new Client();
        client.setCity("Warszawa");
        client.setCountryCode("PL");
        clientService.add(client);

        // --

        Project project = new Project();
        project.setTitle("Projekt 1");
        clientService.addProject(1L, project);

        Team team = new Team();
        team.setTeamType(TeamType.JAVA);
        projectService.addTeam(1L, team);
        teamService.addEmployee(1L, employeeService.getById(1L), EmployeeRole.DESIGNER);
        teamService.addEmployee(1L, employeeService.getById(2L), EmployeeRole.PROGRAMMER);



    }

}

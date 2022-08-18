package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE_WITH_ROLE")
@Setter @Getter
public class EmployeeWithRole {

    @EmbeddedId private EmployeeWithRoleId id = new EmployeeWithRoleId();
    @Enumerated(EnumType.STRING) @Column(name = "employe_role") private Employee.Role employeeRole;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // --
    public void setTeam(Team team) { this.team = team; team.getEmployeeWithRoleSet().add(this); }

    public void setEmployee(Employee employee) { this.employee = employee; employee.getEmployeeWithRoleSet().add(this); }

}

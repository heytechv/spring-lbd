package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.EmployeeRole;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeRoleInTeam")
public class EmployeeRoleInTeam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;

    @Column(name = "team_id") Team team;
    @Column(name = "employee_id") Employee employee;
    @Column(name = "employe_role") EmployeeRole employeeRole;

}

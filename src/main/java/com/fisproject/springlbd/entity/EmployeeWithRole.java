package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.EmployeeRole;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE_ROLE_IN_TEAM")
public class EmployeeWithRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "employe_role") EmployeeRole employeeRole;

}

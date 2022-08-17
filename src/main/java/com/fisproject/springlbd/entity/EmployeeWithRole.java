package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.EmployeeRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE_ROLE_IN_TEAM")
@Setter @Getter
public class EmployeeWithRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "employe_role") private EmployeeRole employeeRole;
    @OneToOne(cascade = CascadeType.ALL) @MapsId private Employee employee;


    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


}

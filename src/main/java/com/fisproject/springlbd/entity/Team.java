package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Client")
@Setter @Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;

    @OneToMany(mappedBy = "team")
    List<EmployeeRoleInTeam> employeeRoleInTeam;




}

package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.TeamType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Team")
@Setter @Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "team_department") private TeamType teamType;

    @OneToMany(mappedBy = "team")
    List<EmployeeWithRole> employeeWithRole;

//    @OneToOne
//    @JoinColumn(name = "project_id")
//    private Project project;







}

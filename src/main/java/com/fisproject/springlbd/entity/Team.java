package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.TeamType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Team")
@Setter @Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "team_department") private TeamType teamType;

//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<EmployeeWithRole> employeeWithRoleList = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeWithRole> employeeWithRoleSet = new HashSet<>();


    // --
    @OneToOne(mappedBy = "team")
    private Project project;




    public void addEmployeeWithRole(EmployeeWithRole employeeWithRole) {
        employeeWithRoleSet.add(employeeWithRole);
    }

    public void removeEmployeeWithRole(EmployeeWithRole employeeWithRole) {
        employeeWithRoleSet.remove(employeeWithRole);
        employeeWithRole.setTeam(null);
    }






}

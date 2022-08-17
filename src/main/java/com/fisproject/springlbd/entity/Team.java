package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.TeamType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Team")
@Setter @Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "team_department") private TeamType teamType;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeWithRole> employeeWithRoleList = new ArrayList<>();


    public void addEmployeeWithRole(EmployeeWithRole employeeWithRole) {
        employeeWithRoleList.add(employeeWithRole);
        employeeWithRole.setTeam(this);
    }

    public void removeEmployeeWithRole(EmployeeWithRole employeeWithRole) {
        employeeWithRoleList.remove(employeeWithRole);
        employeeWithRole.setTeam(null);
    }






}

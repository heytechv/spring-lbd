package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Team")
@Setter @Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Enumerated(EnumType.STRING) @Column(name = "team_type") private Type type;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeWithRole> employeeWithRoleSet = new HashSet<>();

    // --
    @OneToOne(mappedBy = "team")
    private Project project;

    // --
    public void addEmployeeWithRole   (EmployeeWithRole employeeWithRole) { employeeWithRoleSet.add(employeeWithRole); }
    public void removeEmployeeWithRole(EmployeeWithRole employeeWithRole) { employeeWithRoleSet.remove(employeeWithRole); }

    // --
    public enum Type {
        JAVA, NET, SAP
    }

}

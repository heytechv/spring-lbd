package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.CityDepartment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
@Setter @Getter
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name")  private String lastName;
    @Column(name = "contract")   private Contract contract;
    @Column(name = "department") private CityDepartment cityDepartment;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeWithRole> employeeWithRoleSet = new HashSet<>();

    // --
    public enum Contract {
        ETAT, B2B, ZLECENIE
    }

    public enum Role {
        PROGRAMMER, ANALYST, DESIGNER, TESTER, MANAGER
    }

}

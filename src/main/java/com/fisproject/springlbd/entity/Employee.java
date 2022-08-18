package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.CityDepartment;
import com.fisproject.springlbd.entity.enums.EmployeeContract;
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
    @Column(name = "contract")   private EmployeeContract contract;
    @Column(name = "department") private CityDepartment cityDepartment;


    // --
//    @OneToOne(mappedBy = "employee")
//    private EmployeeWithRole employeeWithRole;

//    @OneToMany
//    @JoinTable(name = "EMPLOYEE_WITH_ROLE", joinColumns = "employee_id")
//    private Set<EmployeeWithRole> employeeWithRoleSet = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeWithRole> employeeWithRoleSet = new HashSet<>();


    public void addEmployeeWithRole(EmployeeWithRole employeeWithRole) {
        employeeWithRoleSet.add(employeeWithRole);
    }


}

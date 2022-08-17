package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.CityDepartment;
import com.fisproject.springlbd.entity.enums.EmployeeContract;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Setter @Getter
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "first_name") String firstName;
    @Column(name = "last_name") String lastName;
    @Column(name = "contract") EmployeeContract contract;
    @Column(name = "department") CityDepartment cityDepartment;

//    @OneToOne(mappedBy = "employee")
//    private EmployeeWithRole employeeWithRole;



}

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
    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name")  private String lastName;
    @Column(name = "contract")   private EmployeeContract contract;
    @Column(name = "department") private CityDepartment cityDepartment;


}

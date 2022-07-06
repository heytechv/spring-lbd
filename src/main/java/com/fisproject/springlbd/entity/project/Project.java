package com.fisproject.springlbd.entity.project;

import com.fisproject.springlbd.entity.employee.Employee;
import com.fisproject.springlbd.entity.employee.EmployeeRole;
import com.fisproject.springlbd.entity.faktura.Faktura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project {

    String projectName;
    Faktura faktura;
    Map<EmployeeRole, ArrayList<Employee>> employeesEngaged;

    public Project(String projectName) {
        this.projectName=projectName;
    }

}

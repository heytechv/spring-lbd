package com.fisproject.springlbd.service.client;

import com.fisproject.springlbd.entity.employee.Employee;
import com.fisproject.springlbd.entity.employee.EmployeeRole;
import com.fisproject.springlbd.entity.project.Project;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
//    public abstract void addProject(String projectName);
//    public abstract void addEmployeeToProject(String projectName, EmployeeRole employeeRole, Employee employee);

    public abstract void addProject(Project project);


}

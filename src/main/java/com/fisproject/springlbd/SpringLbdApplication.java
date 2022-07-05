package com.fisproject.springlbd;

import com.fisproject.springlbd.service.employee.EmployeeImp1;
import com.fisproject.springlbd.service.employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringLbdApplication {

    public static final Logger LOG = LoggerFactory.getLogger(SpringLbdApplication.class);

    @Autowired
//    @Qualifier("eimpl1")
    EmployeeService employeeService;

    @PostConstruct
    void init() {
        employeeService.findAll();
//        System.out.println(employeeService.getEmployeeNickname("Michal","Mackowski"));
//        LOG.info(employeeService.getEmployeeNickname("Michal","Mackowski"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);


    }

}

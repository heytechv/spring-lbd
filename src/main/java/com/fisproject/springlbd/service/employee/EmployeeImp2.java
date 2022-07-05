package com.fisproject.springlbd.service.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eimpl2")
public class EmployeeImp2 implements EmployeeService {

    @Override public void findAll() { }

    @Override public String getEmployeeNickname(String firstName, String lastName) {
        return "";
    }
}

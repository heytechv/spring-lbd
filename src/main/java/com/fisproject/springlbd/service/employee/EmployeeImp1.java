package com.fisproject.springlbd.service.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("eimpl1")
//@Primary
public class EmployeeImp1 implements EmployeeService{

    @Value("${moje.prefix}")
    String prefix;

    @Value("${moje.suffix}")
    String suffix;

    @Override public void findAll() { }

    @Override public String getEmployeeNickname(String firstName, String lastName) {
        return prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix;
    }
}

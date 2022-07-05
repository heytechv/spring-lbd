package com.fisproject.springlbd.service.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//@Service("eimpl1")
//@Primary

@Service
@Profile("dev")
public class EmployeeImp1 implements EmployeeService{

    final Logger LOG = LoggerFactory.getLogger(EmployeeImp1.class);

    @Value("${moje.prefix}")
    String prefix;

    @Value("${moje.suffix}")
    String suffix;

    @Override public void findAll() { }

    @Override public String getEmployeeNickname(String firstName, String lastName) {
        LOG.info(firstName+" "+lastName);

        String result = prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix;
        LOG.info(result);

        return result;
    }
}

package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.employee.Employee;
import com.fisproject.springlbd.service.employee.EmployeeServiceImp1;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringLbdApplicationTests {

    Logger LOG = LoggerFactory.getLogger(SpringLbdApplicationTests.class);

    @Autowired
    EmployeeServiceImp1 employeeService;

    @Test void nickname() {
//        EmployeeImp1 employeeImp1 = new EmployeeImp1();

        String firstName = "Michael",
               secondName = "Bednarek";
        String result = employeeService.getEmployeeNickname(firstName, secondName);
        LOG.info(result);

        assert "PREFIXMicBedSUFFIX".equals(result);
    }

    @Test void findByNamePlusSave() {

        employeeService.save(new Employee("Maciek", "Ceislk"));
        employeeService.save(new Employee("Marcin", "Wierow"));
        employeeService.save(new Employee("Marcin", "Wojtkowski"));

        Map<Long, Employee> results = employeeService.findByName("Marcin");

        assert results.size() == 2;
    }


}


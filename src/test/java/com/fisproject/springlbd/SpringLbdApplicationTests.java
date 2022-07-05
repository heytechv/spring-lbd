package com.fisproject.springlbd;

import com.fisproject.springlbd.service.employee.EmployeeImp1;
import com.fisproject.springlbd.service.employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringLbdApplicationTests {

    Logger LOG = LoggerFactory.getLogger(SpringLbdApplicationTests.class);

    @Autowired
    EmployeeImp1 employeeService;

    @Test void nickname() {
//        EmployeeImp1 employeeImp1 = new EmployeeImp1();

        String firstName = "Michael",
               secondName = "Bednarek";
        String result = employeeService.getEmployeeNickname(firstName, secondName);
        LOG.info(result);

        assert "PREFIXMicBedSUFFIX".equals(result);
    }

}


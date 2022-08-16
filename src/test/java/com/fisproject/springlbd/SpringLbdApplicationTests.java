package com.fisproject.springlbd;

import com.fisproject.springlbd.entity.employee.Employee;
import com.fisproject.springlbd.service.employee.EmployeeServiceImp1;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class SpringLbdApplicationTests {

    private final Logger log = LoggerFactory.getLogger(SpringLbdApplicationTests.class);
    @Autowired EmployeeServiceImp1 employeeService;


    /**
     * Nickname test
     * */
    @Test void nickname() {
        String firstName = "Michael",
               secondName = "Bednarek";
        String result = employeeService.getEmployeeNickname(firstName, secondName);
        log.info(result);

        assert "PREFIXMicBedSUFFIX".equals(result);
    }

    /**
     * FindByName + Save test
     * */
    @Test void findByNamePlusSave() {
        employeeService.save(new Employee("Maciek", "Ceislk", "20030404", "ACO42756", "+482317495"));
        employeeService.save(new Employee("Marcin", "Wierow", "13430007", "GPO32451", "+482222222"));
        employeeService.save(new Employee("Marcin", "Wieluk", "95044591", "DCO36852", "+482111111"));

        Map<Long, Employee> results = employeeService.findByName("marcin");
        results.forEach((aLong, employee) -> System.out.println(employee));

        assert results.size() == 2;
    }


}


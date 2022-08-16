package com.fisproject.springlbd.service.employee;

import com.fisproject.springlbd.entity.employee.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//@Service("eimpl1")
//@Primary

@Service
@Profile("dev")
public class EmployeeServiceImp1 implements EmployeeService{

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImp1.class);

    @Value("${moje.prefix}") String prefix;
    @Value("${moje.suffix}") String suffix;


    @Override public void findAll() { }
    @Override public String getEmployeeNickname(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank())
            throw new RuntimeException("First name or/and last name cannot be empty!");

        log.info(firstName+" "+lastName);

        String result = prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix;
        log.info(result);

        return result;
    }

    @Override public Map<Long, Employee> findByName(String nameOrLastName) {
        Map<Long, Employee> resultMap = new HashMap<>();

        dbMap.forEach((aLong, employee) -> {
            if (employee.getImie().equalsIgnoreCase(nameOrLastName) || employee.getNazwisko().equalsIgnoreCase(nameOrLastName))
                resultMap.put((long) resultMap.size(), employee);
        });

        return resultMap;
    }

    @Override public void save(Employee employee) {
        if (employee == null)
            throw new RuntimeException("Employee cannot be null!");
        dbMap.put((long) dbMap.size(), employee);
    }


}

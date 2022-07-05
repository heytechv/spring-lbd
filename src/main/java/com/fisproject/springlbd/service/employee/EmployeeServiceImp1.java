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

    final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImp1.class);
    Map<Long, Employee> dbMap = new HashMap<>();

    @Value("${moje.prefix}") String prefix;
    @Value("${moje.suffix}") String suffix;

    @Override public void findAll() { }
    @Override public String getEmployeeNickname(String firstName, String lastName) {
        LOG.info(firstName+" "+lastName);

        String result = prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix;
        LOG.info(result);

        return result;
    }
    @Override public Map<Long, Employee> findByName(String nameOrLastName) {

//        Map<Long, Employee> rob = new HashMap<>();
//        rob.put(0L, new Employee("Adam", "Cieslik"));
//        rob.put(1L, new Employee("Adam", "Maciejski"));
//        rob.put(2L, new Employee("Michal", "Moslik"));
//        rob.put(3L, new Employee("Andrzej", "Dudowski"));

        Map<Long, Employee> resultMap = new HashMap<>();

        for (Map.Entry<Long, Employee> entry : dbMap.entrySet()) {
            Long k = entry.getKey();
            Employee e = entry.getValue();

            if (e.imie.equals(nameOrLastName) || e.nazwisko.equals(nameOrLastName)) {
                resultMap.put(k, e);
            }
        }

        return resultMap;
    }
    @Override public void save(Employee employee) {
        if (employee == null) return;
        dbMap.put((long) dbMap.size(), employee);
    }



}

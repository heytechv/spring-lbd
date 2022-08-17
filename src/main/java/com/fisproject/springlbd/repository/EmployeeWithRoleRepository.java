package com.fisproject.springlbd.repository;

import com.fisproject.springlbd.entity.Employee;
import com.fisproject.springlbd.entity.EmployeeWithRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeWithRoleRepository extends CrudRepository<EmployeeWithRole, Long> {
}

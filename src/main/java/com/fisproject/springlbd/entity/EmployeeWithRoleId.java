package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter @Getter
public class EmployeeWithRoleId implements Serializable {

    @Column(name = "team_id") private Long teamId;
    @Column(name = "employee_id") private Long employeeId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmployeeWithRoleId that = (EmployeeWithRoleId) o;
        return Objects.equals(teamId, that.teamId) && Objects.equals(employeeId, that.employeeId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(teamId, employeeId);
    }


}

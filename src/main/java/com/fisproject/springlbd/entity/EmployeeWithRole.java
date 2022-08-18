package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.EmployeeRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEE_WITH_ROLE")
@Setter @Getter
public class EmployeeWithRole {

    @EmbeddedId private EmployeeWithRoleId id = new EmployeeWithRoleId();
    @Column(name = "employe_role") private EmployeeRole employeeRole;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public void setTeam(Team team) {
        this.team = team;
        team.getEmployeeWithRoleSet().add(this);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        employee.getEmployeeWithRoleSet().add(this);
    }

    //
//    @Column(name = "team_id") private Long teamId;
//
//    @Column(name = "employee_id") private Long employeeId;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        EmployeeWithRole that = (EmployeeWithRole) o;
//        return Objects.equals(teamId, that.teamId) &&
//                Objects.equals(employeeId, that.employeeId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(teamId, employeeId);
//    }


//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
//    @Column(name = "employe_role") private EmployeeRole employeeRole;
////    @OneToOne @MapsId private Employee employee;
//
//
////    @OneToOne(cascade = CascadeType.ALL)
////    @JoinColumn(name = "employee_id", referencedColumnName = "id")
////    private Employee employee;
//
//
//
//    // --
//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;

//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//        employee.setEmployeeWithRole(this);
//    }


}

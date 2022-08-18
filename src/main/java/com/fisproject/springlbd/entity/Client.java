package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.service.ProjectServiceImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Client")
@Setter @Getter
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "country_code") private String countryCode;
    @Column(name = "city") private String city;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Project> projectList = new ArrayList<>();


    public void addProject(Project project) {
        projectList.add(project);
        project.setClient(this);
    }

    public void removeProject(Project project) {
        projectList.remove(project);
        project.setClient(null);
    }


}

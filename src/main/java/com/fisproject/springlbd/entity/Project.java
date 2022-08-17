package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Project")
@Setter @Getter
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;

    @Column(name = "title") private String title;

//    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Team team;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @OneToOne
    @MapsId
    private Team team;


    /**
     * */
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Project )) return false;
//        return id != null && id.equals(((Project) o).getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }

}

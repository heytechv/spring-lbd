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
//    @OneToOne @MapsId private Team team;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;


    // --
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;



    // Dziala i z tym i bez :/
    public void setTeam(Team team) {
        this.team = team;
        team.setProject(this);
    }


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

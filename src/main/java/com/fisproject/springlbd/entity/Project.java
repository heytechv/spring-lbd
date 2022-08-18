package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@Setter @Getter
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "title") private String title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprintList = new ArrayList<>();

    @OneToMany(mappedBy = "projectX", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoiceList = new ArrayList<>();

    // --
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //--
    public void setTeam(Team team) { this.team = team; team.setProject(this); }

    public void addSprint   (Sprint sprint) { sprintList.add(sprint); sprint.setProject(this); }
    public void removeSprint(Sprint sprint) { sprintList.remove(sprint); sprint.setProject(null); }

    public void addInvoice   (Invoice invoice) { invoiceList.add(invoice); invoice.setProjectX(this); }
    public void removeInvoice(Invoice invoice) { invoiceList.remove(invoice); invoice.setProjectX(null); }

}

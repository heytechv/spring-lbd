package com.fisproject.springlbd.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Sprint")
public class Sprint {

    @Id @GeneratedValue private Long id;
    private String name;
    private Timestamp start_date, end_date;
    private String description, status;

    @Column(name="id")
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    @Column(name="name")
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @Column(name="start_date")
    public void setStart_date(Timestamp start_date) { this.start_date = start_date; }
    public Timestamp getStart_date() { return start_date; }

    @Column(name="end_date")
    public void setEnd_date(Timestamp end_date) { this.end_date = end_date; }
    public Timestamp getEnd_date() { return end_date; }

    @Column(name="description")
    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    @Column(name="status")
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

}

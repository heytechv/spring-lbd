package com.fisproject.springlbd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="Sprint")
public class Sprints {

    @Id @GeneratedValue private Long id;
    private String name;
    private Timestamp start_date, end_date;
    private String description, status;
}

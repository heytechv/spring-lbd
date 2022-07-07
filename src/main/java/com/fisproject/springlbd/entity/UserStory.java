package com.fisproject.springlbd.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="UserStory")
public class UserStory {

    @Id @GeneratedValue private Long id;
    private String name, description;
    private Integer story_points_amount;
    private String status;

    @Column(name="id")
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    @Column(name="name")
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @Column(name="story_points_amount")
    public void setStory_points_amount(Integer story_points_amount) { this.story_points_amount = story_points_amount; }
    public Integer getStory_points_amount() { return story_points_amount; }


    @Column(name="description")
    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    @Column(name="status")
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

}

package com.fisproject.springlbd.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERSTORY")
public class UserStory {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;                                              // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    private String name, description;
    private Integer story_points_amount;
    private String status;

    @OneToMany(mappedBy = "userStoryLinked")
    private List<Attachment> attachments = new ArrayList<>();

    @ManyToMany(mappedBy="userStories")     // tutaj nazwa zmiennej List w Sprint.java
    private List<Sprint> sprints = new ArrayList<>();

    @Column(name="id") public void setId(Long id) { this.id = id; }
    @Column(name="id") public Long getId() { return id; }

    @Column(name="name") public void setName(String name) { this.name = name; }
    @Column(name="name") public String getName() { return name; }

    @Column(name="description") public void setDescription(String description) { this.description = description; }
    @Column(name="description") public String getDescription() { return description; }

    @Column(name="story_points_amount") public void setStory_points_amount(Integer story_points_amount) { this.story_points_amount = story_points_amount; }
    @Column(name="story_points_amount") public Integer getStory_points_amount() { return story_points_amount; }

    @Column(name="status") public void setStatus(String status) { this.status = status; }
    @Column(name="status") public String getStatus() { return status; }

}

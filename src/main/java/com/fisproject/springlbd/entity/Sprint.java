package com.fisproject.springlbd.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Sprint")
public class Sprint {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;                                              // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    private String name;
    private Timestamp startDate, endDate;
    private String description, status;

    /* Sprint - jako glowny (UserStory poboczny?) */
    @ManyToMany(fetch = FetchType.EAGER)                                                                                                         // https://www.youtube.com/watch?v=ntN1HWKND8U&ab_channel=CodeForgeYT
    @JoinTable(
        name="SPRINT_USER_STORY",
            joinColumns       =@JoinColumn(name="sprint_id"),
            inverseJoinColumns=@JoinColumn(name="user_story_id")
    )
    private List<UserStory> userStories = new ArrayList<>();

    @Column(name="id")
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    @Column(name="name")
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @Column(name="start_date")
    public void setStartDate(Timestamp start_date) { this.startDate = start_date; }
    public Timestamp getStartDate() { return startDate; }

    @Column(name="end_date")
    public void setEndDate(Timestamp end_date) { this.endDate = end_date; }
    public Timestamp getEndDate() { return endDate; }

    @Column(name="description")
    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    @Column(name="status")
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

    public List<UserStory> getUserStories() { return userStories; }
    public void addUserStory(UserStory userStory) { this.userStories.add(userStory); }
}

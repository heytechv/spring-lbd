package com.fisproject.springlbd.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<UserStory> userStories = new HashSet<>();

    @Column(name="id") public void setId(Long id) { this.id = id; }
    @Column(name="id") public Long getId() { return id; }

    @Column(name="name") public void setName(String name) { this.name = name; }
    @Column(name="name") public String getName() { return name; }

    @Column(name="start_date") public void setStartDate(Timestamp start_date) { this.startDate = start_date; }
    @Column(name="start_date") public Timestamp getStartDate() { return startDate; }

    @Column(name="end_date") public void setEndDate(Timestamp end_date) { this.endDate = end_date; }
    @Column(name="end_date") public Timestamp getEndDate() { return endDate; }

    @Column(name="description") public void setDescription(String description) { this.description = description; }
    @Column(name="description") public String getDescription() { return description; }

    @Column(name="status") public void setStatus(StatusType status) { this.status = status.toString(); }
    @Column(name="status") public String getStatus() { return status; }

    public Set<UserStory> getUserStories() { return userStories; }
    public void addUserStory(UserStory userStory) { this.userStories.add(userStory); }


    public enum StatusType {
        PENDING,
        IN_PROGRESS,
        FINISHED,
        CANCELED
    }


}

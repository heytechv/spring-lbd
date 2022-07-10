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

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)                                                               // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    @Column(name="id")          private Long id;
    @Column(name="name")        private String name;
    @Column(name="start_date")  private Timestamp startDate;
    @Column(name="end_date")    private Timestamp endDate;
    @Column(name="description") private String description;
    @Enumerated(EnumType.STRING) @Column(name="status") private StatusType status;

    /* Sprint - jako glowny (UserStory poboczny?) */
    @ManyToMany(fetch = FetchType.EAGER)                                                                                                         // https://www.youtube.com/watch?v=ntN1HWKND8U&ab_channel=CodeForgeYT
    @JoinTable(
        name="SPRINT_USER_STORY",
            joinColumns       =@JoinColumn(name="sprint_id"),
            inverseJoinColumns=@JoinColumn(name="user_story_id")
    )
    private Set<UserStory> userStories = new HashSet<>();

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setStartDate(Timestamp start_date) { this.startDate = start_date; }
    public Timestamp getStartDate() { return startDate; }

    public void setEndDate(Timestamp end_date) { this.endDate = end_date; }
    public Timestamp getEndDate() { return endDate; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setStatus(StatusType status) { this.status = status; }
    public StatusType getStatus() { return status; }

    public Set<UserStory> getUserStories() { return userStories; }
    public void addUserStory(UserStory userStory) { this.userStories.add(userStory); }
    public void removeUserStory(UserStory userStory) {
        this.userStories.remove(userStory);
        userStory.getSprints().remove(this);
    }

    public enum StatusType {
        PENDING, IN_PROGRESS, FINISHED, CANCELED
    }


}

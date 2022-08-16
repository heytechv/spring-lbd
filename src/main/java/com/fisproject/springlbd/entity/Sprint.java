package com.fisproject.springlbd.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Sprint")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Sprint {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)                                                               // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    @Column(name="id")          private Long id;
    @Column(name="name")        private String name;
    @Column(name="start_date")  private Timestamp startDate;
    @Column(name="end_date")    private Timestamp endDate;
    @Column(name="description") private String description;
    @Column(name="status")      private String status;

    /* Sprint - jako glowny (UserStory dodatkowy) */
    @ManyToMany(fetch = FetchType.EAGER)                                                                                // https://www.youtube.com/watch?v=ntN1HWKND8U&ab_channel=CodeForgeYT
    @JoinTable(
        name="SPRINT_USER_STORY",
            joinColumns       =@JoinColumn(name="sprint_id"),
            inverseJoinColumns=@JoinColumn(name="user_story_id")
    )
    @Builder.Default private List<UserStory> userStoryList = new ArrayList<>();


    public void setStatus(StatusType status) { this.status = status.toString(); }
    public void addUserStory(UserStory userStory) { this.userStoryList.add(userStory); }

    public enum StatusType {
        PENDING,
        IN_PROGRESS,
        FINISHED,
        CANCELED
    }

}

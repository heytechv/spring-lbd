package com.fisproject.springlbd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERSTORY")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserStory {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)                                                               // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    @Column(name="id")                  private Long id;
    @Column(name="name")                private String name;
    @Column(name="description")         private String description;
    @Column(name="story_points_amount") private Integer storyPointsAmount;
    @Column(name="status")              private String status;

    @OneToMany(mappedBy = "userStoryLinked")
    private List<Attachment> attachmentList = new ArrayList<>();

    @ManyToMany(mappedBy= "userStoryList")     // tutaj nazwa zmiennej List w Sprint.java
    private List<Sprint> sprintList = new ArrayList<>();


    public void setStatus(StatusType status) { this.status = status.toString(); }

    public enum StatusType {
        TO_DO, IN_PROGRESS, REVIEW, DONE
    }

}

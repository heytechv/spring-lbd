package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.SprintStatus;
import com.fisproject.springlbd.entity.enums.UserStoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER_STORY")
@Setter @Getter
public class UserStory {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)                                                               // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    @Column(name="id")                  private Long id;
    @Column(name="name")                private String name;
    @Column(name="description")         private String description;
    @Column(name="story_points_amount") private Integer storyPointsAmount;
    @Enumerated(EnumType.STRING) @Column(name="status") private UserStoryStatus status;

    /** Tutaj nazwa zmiennej List w Sprint.java */
    @ManyToMany(mappedBy="userStorySet")
    private Set<Sprint> sprintSet = new HashSet<>();

    /** SprintSet
     * Removing/adding ManyToMany
     * <a href="https://www.youtube.com/watch?v=vYNdjtf7iAQ&ab_channel=ThorbenJanssen">...</a>
     * */
    public void removeFromLinkedSprints() {
        for (Sprint sprint : getSprintSet()) {
            sprint.removeUserStory(this);
        }
    }

}

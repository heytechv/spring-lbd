package com.fisproject.springlbd.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
    @Enumerated(EnumType.STRING) @Column(name="status") private StatusType status;

    /** Sprint - jako glowny (UserStory dodatkowy) */
    @ManyToMany(fetch = FetchType.EAGER)                                                                                // https://www.youtube.com/watch?v=ntN1HWKND8U&ab_channel=CodeForgeYT
    @JoinTable(
        name="SPRINT_USER_STORY",
            joinColumns       =@JoinColumn(name="sprint_id"),
            inverseJoinColumns=@JoinColumn(name="user_story_id")
    )
    @Builder.Default private Set<UserStory> userStorySet = new HashSet<>();                                             // Default init

    /** UserStorySet
     * Removing/adding ManyToMany
     * <a href="https://www.youtube.com/watch?v=vYNdjtf7iAQ&ab_channel=ThorbenJanssen">...</a>
     * */
    public void addUserStory(UserStory userStory) {
        this.userStorySet.add(userStory);
        userStory.getSprintSet().add(this);
    }
    public void removeUserStory(UserStory userStory) {
        this.userStorySet.remove(userStory);
        userStory.getSprintSet().remove(this);
    }

    /**
     * Sprint status types
     * */
    public enum StatusType {
        PENDING,
        IN_PROGRESS,
        FINISHED,
        CANCELED
    }

}

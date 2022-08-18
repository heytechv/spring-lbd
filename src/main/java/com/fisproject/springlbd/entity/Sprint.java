package com.fisproject.springlbd.entity;

import com.fisproject.springlbd.entity.enums.SprintStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sprint")
@Setter @Getter
public class Sprint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private Long id;
    @Column(name="name")        private String name;
    @Column(name="start_date")  private Timestamp startDate;
    @Column(name="end_date")    private Timestamp endDate;
    @Column(name="description") private String description;
    @Enumerated(EnumType.STRING) @Column(name="status") private SprintStatus status;

    /** Sprint - jako glowny (UserStory dodatkowy) */
    @ManyToMany(fetch = FetchType.EAGER)                                                                                // https://www.youtube.com/watch?v=ntN1HWKND8U&ab_channel=CodeForgeYT
    @JoinTable(
            name="SPRINT_USER_STORY",
            joinColumns       =@JoinColumn(name="sprint_id"),
            inverseJoinColumns=@JoinColumn(name="user_story_id")
    )
    private Set<UserStory> userStorySet = new HashSet<>();                                             // Default init


    // --
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

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

}

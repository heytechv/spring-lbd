package com.fisproject.springlbd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Enumerated(EnumType.STRING) @Column(name="status") private StatusType status;

    @OneToMany(mappedBy = "userStoryLinked")
    @Builder.Default private Set<Attachment> attachmentSet = new HashSet<>();

    /** Tutaj nazwa zmiennej List w Sprint.java */
    @ManyToMany(mappedBy="userStorySet")
    @Builder.Default private Set<Sprint> sprintSet = new HashSet<>();

    /** SprintSet
     * Removing/adding ManyToMany
     * <a href="https://www.youtube.com/watch?v=vYNdjtf7iAQ&ab_channel=ThorbenJanssen">...</a>
     * */
    public void removeFromLinkedSprints() {
        for (Sprint sprint : getSprintSet()) {
            sprint.removeUserStory(this);
        }
    }

    /**
     * Attachment
     * */
    public void addAttachment(Attachment attachment) { this.attachmentSet.add(attachment); }
    public void removeAttachment(Attachment attachment) {
        this.attachmentSet.remove(attachment);
        attachment.setUserStoryLinked(null);
    }

    /**
     * UserStory status types
     * */
    public enum StatusType {
        TO_DO,
        IN_PROGRESS,
        REVIEW,
        DONE
    }

}

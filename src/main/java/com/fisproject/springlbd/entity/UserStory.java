package com.fisproject.springlbd.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USERSTORY")
public class UserStory {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)                                                               // https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement - @GeneratedValue(...)
    @Column(name="id")                  private Long id;
    @Column(name="name")                private String name;
    @Column(name="description")         private String description;
    @Column(name="story_points_amount") private Integer story_points_amount;
    @Enumerated(EnumType.STRING) @Column(name="status") private StatusType status;

    @OneToMany(mappedBy = "userStoryLinked")
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToMany(mappedBy="userStories")     // tutaj nazwa zmiennej List w Sprint.java
    private Set<Sprint> sprints = new HashSet<>();

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setStoryPointsAmount(Integer story_points_amount) { this.story_points_amount = story_points_amount; }
    public Integer getStoryPointsAmount() { return story_points_amount; }

    public void setStatus(StatusType status) { this.status = status; }
    public StatusType getStatus() { return status; }

    public Set<Sprint> getSprints() { return sprints; }
    public void setSprints(Set<Sprint> sprints) { this.sprints = sprints; }
    public void removeFromLinkedSprints() {
        for (Sprint sprint : getSprints()) {
            sprint.removeUserStory(this);
        }
    }

    public Set<Attachment> getAttachments() { return attachments; }
    public void addAttachment(Attachment attachment) { this.attachments.add(attachment); }
    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }


    public enum StatusType {
        TO_DO, IN_PROGRESS, REVIEW, DONE
    }

}

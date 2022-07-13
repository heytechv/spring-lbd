package com.fisproject.springlbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "Attachment")
public class Attachment {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    private UserStory userStoryLinked;

    @Column(name = "binary_file")
    private byte[] binaryFile;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setUserStoryLinked(UserStory userStoryId) { this.userStoryLinked =userStoryId; }
    public UserStory getUserStoryLinked() { return userStoryLinked; }

    public void setBinaryFile(byte[] binaryFile) { this.binaryFile=binaryFile; }
    public byte[] getBinaryFile() { return binaryFile; }

}


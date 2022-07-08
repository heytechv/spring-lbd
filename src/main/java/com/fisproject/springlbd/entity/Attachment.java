package com.fisproject.springlbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "Attachment")
public class Attachment {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    private Sprint userStoryLinked;
    private byte[] binaryFile;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    @Column(name = "user_story_id")
    public void setUserStoryLinked(Sprint userStoryId) { this.userStoryLinked =userStoryId; }
    public Sprint getUserStoryLinked() { return userStoryLinked; }

    @Column(name = "binary_file")
    public void setBinaryFile(byte[] binaryFile) { this.binaryFile=binaryFile; }
    public byte[] getBinaryFile() { return binaryFile; }

}


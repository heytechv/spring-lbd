package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Attachment")
@Setter @Getter
public class Attachment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "binary_file") private byte[] binaryFile;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    private UserStory userStory;

}


package com.fisproject.springlbd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SprintUserStory")
public class SprintUserStory {

    @Id @GeneratedValue Long id;
    Long spring_id, user_story_id;

}

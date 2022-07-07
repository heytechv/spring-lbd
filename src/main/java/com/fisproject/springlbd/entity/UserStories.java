package com.fisproject.springlbd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserStory")
public class UserStories {

    @Id @GeneratedValue private Long id;
    private String name, description;
    private Integer story_points_amount;
    private String status;

}

package com.fisproject.springlbd.entity;

public class SprintDTO {

    private Long id;
    private String name, description, status;

    public SprintDTO(Long id, String name, String description) {
        this.id=id;
        this.name=name;
        this.description=description;
    }

}

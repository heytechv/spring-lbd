package com.fisproject.springlbd.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class UserStoryCreatedEvent {

    private Long userStoryId;

}

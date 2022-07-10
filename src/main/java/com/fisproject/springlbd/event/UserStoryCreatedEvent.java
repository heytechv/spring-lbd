package com.fisproject.springlbd.event;

public class UserStoryCreatedEvent {

    private Long userStoryId;

    public UserStoryCreatedEvent(Long id) {
        this.userStoryId=id;
    }

    public Long getUserStoryId() { return userStoryId; }
    public void setUserStoryId(Long userStoryId) { this.userStoryId = userStoryId; }

}

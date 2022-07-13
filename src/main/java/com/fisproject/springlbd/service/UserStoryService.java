package com.fisproject.springlbd.service;


import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.UserStoryZad2Dto;
import com.fisproject.springlbd.dto.UserStoryZad5Dto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserStoryService {
    UserStory createUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status) throws IllegalArgumentException;
    UserStory createUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status, boolean shouldSave) throws IllegalArgumentException;
    Page<UserStory> findAllByPage(Integer page, Integer size);
    List<UserStory> findAll();
    Optional<UserStory> findById(Long id);
    Page<UserStory> findAllPageAndSortByName(Integer page, Integer limit);
    void delete(UserStory userStory);


    /** ------------------------------------------------------------------------------------ **
    /** -- Day3 - web responses ------------------------------------------------------------ **
    /** ------------------------------------------------------------------------------------ **/
    StandardResponse getUserStoryDescription(Long userStoryId);
    StandardResponse getSortedUserStories(Integer page, Integer limit);
    StandardResponse addAttachment(Long userStoryId, Attachment attachment, boolean shouldSaveAttachment);
    StandardResponse getAttachmentList(Long userStoryId);

    /** ------------------------------------------------------------------------------------ **
    /** -- Mapper -------------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    UserStoryZad2Dto convertEntityToZad2Dto(UserStory userStory);
    UserStoryZad5Dto convertEntityToZad5Dto(UserStory userStory);

}

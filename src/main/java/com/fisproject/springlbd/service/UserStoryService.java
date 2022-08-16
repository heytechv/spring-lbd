package com.fisproject.springlbd.service;


import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.AttachmentDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserStoryService {
    UserStory createUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status) throws IllegalArgumentException;
    UserStory createUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status, boolean shouldSave) throws IllegalArgumentException;
    Page<UserStory> findAllByPage(Integer page, Integer size);
    List<UserStory> findAll();
//    Optional<UserStory> findById(Long id);

    /** ------------------------------------------------------------------------------------ **
    /** -- Day3 - web responses ------------------------------------------------------------ **
    /** ------------------------------------------------------------------------------------ **/
    String getDescription(Long userStoryId);
    List<UserStoryDto> getSortedUserStories(Integer page, Integer limit);
    StandardResponse addAttachment(Long userStoryId, Attachment attachment, boolean shouldSaveAttachment);
    List<AttachmentDto> getAttachmentList(Long userStoryId);
    StandardResponse getAttachmentById(Long userStoryId, Long attachmentId);

    void deleteById(Long userStoryId);

    /** ------------------------------------------------------------------------------------ **
    /** -- Mapper -------------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    UserStoryDto convertEntityToZad2Dto(UserStory userStory);
    UserStoryDto convertEntityToZad5Dto(UserStory userStory);

}

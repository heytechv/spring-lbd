package com.fisproject.springlbd.service;


import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.AttachmentDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

public interface UserStoryService {

    void add(@Valid UserStoryDto userStoryDto);
    void addAll(List<@Valid UserStoryDto> userStoryDtoList);

    List<UserStory> getAll();
    Page<UserStory> getAllByPage(Integer page, Integer size);
    UserStory getById(Long id);
    String getDescription(Long userStoryId);
    List<UserStoryDto> getSortedUserStoryList(Integer page, Integer limit);

    void deleteById(Long userStoryId);

    void addAttachment(Long userStoryId, @Valid AttachmentDto attachmentDto);
    List<AttachmentDto> getAttachmentList(Long userStoryId);



}

package com.fisproject.springlbd.service;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.AttachmentDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.mapper.UserStoryMapper;
import com.fisproject.springlbd.repository.AttachmentRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {

    UserStoryRepository userStoryRepository;
    AttachmentRepository attachmentRepository;
    UserStoryMapper userStoryMapper;

    private final Logger log = LoggerFactory.getLogger(UserStoryServiceImpl.class);

    /**
     * Private (utilities)
     * */
    private UserStory findById(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserStory with id="+id+" not found!"));
    }

    private void delete(UserStory userStory) {
//        for (Attachment a : userStory.getAttachmentSet()) {
//            userStory.removeAttachment(a);
//            attachmentRepository.delete(a);
//        }
        // todo sprawdzic czy usuwa attachments


        userStory.removeFromLinkedSprints();
        userStoryRepository.delete(userStory);
    }

    private Page<UserStory> findAllPageAndSortByName(Integer page, Integer limit) {
        return userStoryRepository.findAll(PageRequest.of(page, limit, Sort.by("name").ascending()));
    }

    /**
     * Public
     * */
    @Override public List<UserStory> getAll() {
        return (List<UserStory>) userStoryRepository.findAll();
    }

    @Override public Page<UserStory> getAllByPage(Integer page, Integer size) {
        return userStoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override public void deleteById(Long userStoryId) {
        delete(findById(userStoryId));
    }

    @Override public List<UserStoryDto> getSortedUserStoryList(Integer page, Integer limit) {
        return findAllPageAndSortByName(page, limit).stream().map(userStory ->
                userStoryMapper.userStoryToDto(userStory)).collect(Collectors.toList());
    }

    /** (stworzone do Zad 6) */
    @Override public String getDescription(Long id) {
        return findById(id).getDescription();
    }

    /** (stworzone do Zad 7) */
    @Override @Transactional public void addAttachment(Long id, AttachmentDto attachmentDto) {
        UserStory userStory = findById(id);

        Attachment attachment = new Attachment();
        attachment.setBinaryFile(attachmentDto.getBinaryFile());
        attachment.setUserStory(userStory);
        attachmentRepository.save(attachment);

        userStory.addAttachment(attachment);
        userStoryRepository.save(userStory);
    }

    /** (stworzone do Zad 8) */
    @Override public List<AttachmentDto> getAttachmentList(Long id) {
        UserStory userStory = findById(id);
        return userStory.getAttachmentSet()
                .stream().map(attachment -> new AttachmentDto(attachment.getId(), attachment.getBinaryFile())).collect(Collectors.toList());
    }

//    /** (stworzone do Zad 8) */
//    @Override public StandardResponse getAttachmentById(Long userStoryId, Long attachmentId) {
//        Optional<UserStory> optionalUserStory = userStoryRepository.findById(userStoryId);
//
//        if (optionalUserStory.isEmpty())
//            return new StandardResponse(HttpStatus.BAD_REQUEST, "", "User story with that id not found");
//
//        return optionalUserStory.map(userStory -> {
//            List<AttachmentDto> attachmentDtoList = userStory
//                    .getAttachmentSet().stream().map(attachment ->
//                            new AttachmentDto(attachment.getId(), attachment.getBinaryFile())
//                    ).collect(Collectors.toList());
//
//                    return new StandardResponse(HttpStatus.OK, attachmentDtoList, "found");
//                }).orElse(new StandardResponse(HttpStatus.BAD_REQUEST, "", "not found"));
//    }

}

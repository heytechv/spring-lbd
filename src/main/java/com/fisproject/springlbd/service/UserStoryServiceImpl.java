package com.fisproject.springlbd.service;

import com.fisproject.springlbd.dto.AttachmentDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.mapper.UniversalMapper;
import com.fisproject.springlbd.repository.AttachmentRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class UserStoryServiceImpl implements UserStoryService {

    UserStoryRepository userStoryRepository;
    AttachmentRepository attachmentRepository;
    UniversalMapper universalMapper;

    private final Logger log = LoggerFactory.getLogger(UserStoryServiceImpl.class);

    /**
     * Private (utilities)
     * */
    private UserStory findById(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserStory with id="+id+" not found!"));
    }

    private void delete(UserStory userStory) {
        userStory.removeFromLinkedSprints();
        userStoryRepository.delete(userStory);
    }

    private Page<UserStory> findAllPageAndSortByName(Integer page, Integer limit) {
        return userStoryRepository.findAll(PageRequest.of(page, limit, Sort.by("name").ascending()));
    }

    /**
     * Public
     * */
    @Override @Transactional public void add(@Valid UserStoryDto userStoryDto) {
        if (userStoryDto == null)
            throw new RuntimeException("UserStory cannot be null!");
        userStoryRepository.save(universalMapper.userStoryDtoToUserStory(userStoryDto));
    }

    @Override @Transactional public void addAll(List<@Valid UserStoryDto> userStoryDtoList) {
        if (userStoryDtoList == null)
            throw new RuntimeException("UserStory cannot be null!");
        userStoryRepository.saveAll(universalMapper.userStoryDtoListToList(userStoryDtoList));
    }

    @Override public List<UserStory> getAll() {
        return (List<UserStory>) userStoryRepository.findAll();
    }

    @Override public Page<UserStory> getAllByPage(Integer page, Integer size) {
        return userStoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override public UserStory getById(Long  id) {
        return findById(id);
    }

    @Override public void deleteById(Long userStoryId) {
        delete(findById(userStoryId));
    }

    @Override public List<UserStoryDto> getSortedUserStoryList(Integer page, Integer limit) {
        return findAllPageAndSortByName(page, limit).stream().map(userStory ->
                universalMapper.userStoryToDto(userStory)).collect(Collectors.toList());
    }

    @Override public String getDescription(Long id) {
        /* Zad 6 */
        return findById(id).getDescription();
    }

    @Override @Transactional public void addAttachment(Long id, AttachmentDto attachmentDto) {
        /* Zad 7 */
        UserStory userStory = findById(id);

        Attachment attachment = new Attachment();
        attachment.setBinaryFile(attachmentDto.getBinaryFile());
        attachment.setUserStory(userStory);
        attachmentRepository.save(attachment);

        userStory.addAttachment(attachment);
        userStoryRepository.save(userStory);
    }

    @Override public List<AttachmentDto> getAttachmentList(Long id) {
        /* Zad 8 */
        UserStory userStory = findById(id);
        return userStory.getAttachmentSet() .stream().map(attachment ->
                new AttachmentDto(attachment.getId(), attachment.getBinaryFile())).collect(Collectors.toList());
    }

}

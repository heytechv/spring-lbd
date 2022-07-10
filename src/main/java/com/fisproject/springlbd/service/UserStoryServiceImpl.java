package com.fisproject.springlbd.service;

import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.UserStoryZad2Dto;
import com.fisproject.springlbd.dto.UserStoryZad5Dto;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired UserStoryRepository userStoryRepository;

    @Override @Transactional
    public UserStory createUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status, boolean shouldSave) throws IllegalArgumentException {

        UserStory userStory = new UserStory();
        userStory.setName(name);
        userStory.setDescription(description);
        if (story_points_amount != null) userStory.setStoryPointsAmount(story_points_amount);

        userStory.setStatus(UserStory.StatusType.TO_DO);
        if (status != null && Arrays.asList(UserStory.StatusType.values()).contains(status))
            userStory.setStatus(status);

        if (shouldSave) userStoryRepository.save(userStory);

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("[addUserStory] Missing required 'name' field!");
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("[addUserStory] Missing required 'description' field!");

        return userStory;
    }
    @Override public UserStory createUserStory(String name, String description, Integer story_points_amount, UserStory.StatusType status) throws IllegalArgumentException {
        return createUserStory(name, description, story_points_amount, status, true);
    }

    @Override public List<UserStory> findAll() {
        return (List<UserStory>) userStoryRepository.findAll();
    }

    @Override public Page<UserStory> findAllByPage(Integer page, Integer size) {
        return userStoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override public Optional<UserStory> findById(Long id) {
        return userStoryRepository.findById(id);
    }

    @Override public Page<UserStory> findAllPageAndSortByName(Integer page, Integer limit) {
        return userStoryRepository.findAll(PageRequest.of(page, limit, Sort.by("name")));
    }

    @Override public void delete(UserStory userStory) {
        userStory.removeFromLinkedSprints();
        userStoryRepository.delete(userStory);
    }

    /** ------------------------------------------------------------------------------------ **
    /** -- Day3 - web responses ------------------------------------------------------------ **
    /** ------------------------------------------------------------------------------------ **/
    /** (stworzone do Zad 6) */
    @Override public StandardResponse getUserStoryDescription(Long userStoryId) {
        Optional<UserStory> optionalUserStory = findById(userStoryId);
        if (optionalUserStory.isEmpty())
            return new StandardResponse(HttpStatus.BAD_REQUEST, "", "Not found!");

        return new StandardResponse(HttpStatus.OK, optionalUserStory.get().getDescription(), "foud");
    }

    @Override public StandardResponse getSortedUserStories(Integer page, Integer limit) {
        return new StandardResponse(
                HttpStatus.OK,
                findAllPageAndSortByName(page, limit).stream().map(this::convertEntityToZad5Dto).collect(Collectors.toList()),
                "found"
        );
    }

    /** ------------------------------------------------------------------------------------ **
    /** -- Mapper -------------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    @Override public UserStoryZad2Dto convertEntityToZad2Dto(UserStory userStory) {
        return new UserStoryZad2Dto(userStory.getId(), userStory.getName(), userStory.getStoryPointsAmount());
    }

    @Override public UserStoryZad5Dto convertEntityToZad5Dto(UserStory userStory) {
        return new UserStoryZad5Dto(userStory.getId(), userStory.getName(), userStory.getStoryPointsAmount(), userStory.getStatus());
    }


}

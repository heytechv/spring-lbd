package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.Team;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.ProjectRepository;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SprintServiceImpl {

    private SprintRepository sprintRepository;
    private UserStoryRepository userStoryRepository;

    /** Private
     * */
    private Sprint findById(Long id) {
        return sprintRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }



    /** Public
     * */
    public Sprint getById(Long id) {
        return findById(id);
    }

    public List<UserStory> getUserStoryList(Long id) {
        return new ArrayList<>(findById(id).getUserStorySet());
    }


    /** UserStory to Sprint by id */
    @Transactional
    public void addUserStory(Long id, UserStory userStory) {
        if (userStory == null)
            throw new RuntimeException("UserStory cannot be null!");
        Sprint sprint = findById(id);
        sprint.addUserStory(userStory);
        userStoryRepository.save(userStory);

        sprintRepository.save(sprint);
    }

}

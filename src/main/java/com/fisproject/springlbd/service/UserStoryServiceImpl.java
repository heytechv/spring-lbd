package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserStoryServiceImpl {

    private UserStoryRepository userStoryRepository;
    private SprintRepository sprintRepository;

    /** Private
     * */
    private UserStory findById(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }


    private void remove(UserStory userStory) {
        if (userStory == null)
            throw new RuntimeException("UserStory cannot be null!");
        userStory.removeFromLinkedSprints();
        userStoryRepository.delete(userStory);
    }



    /** Public
     * */
    public UserStory getById(Long id) {
        return findById(id);
    }


    @Transactional public void removeById(Long id) {
        remove(findById(id));
    }

}

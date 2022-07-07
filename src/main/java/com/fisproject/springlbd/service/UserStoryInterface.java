package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserStoryInterface extends CrudRepository<UserStory, Long> {
    Optional<UserStory> findByName(String name);
}

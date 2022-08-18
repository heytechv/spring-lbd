package com.fisproject.springlbd.repository;

import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
}

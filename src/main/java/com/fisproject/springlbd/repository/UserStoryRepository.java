package com.fisproject.springlbd.repository;


import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
public interface UserStoryRepository extends PagingAndSortingRepository<UserStory, Long> {
    Optional<UserStory> findByName(String name);

}

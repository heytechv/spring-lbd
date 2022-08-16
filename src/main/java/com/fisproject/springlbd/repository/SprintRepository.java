package com.fisproject.springlbd.repository;


import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
//public interface SprintRepository extends CrudRepository<Sprint, Long> {
public interface SprintRepository extends PagingAndSortingRepository<Sprint, Long> {
    Optional<Sprint> findByName(String name);

    @Query("SELECT s FROM Sprint s WHERE s.startDate BETWEEN :startRange AND :endRange")                               // @Param("nazwa") i potem w sql ":nazwa"
    Optional<List<Sprint>> getSprintListBetweenDates(@Param("startRange") Timestamp start_range, @Param("endRange") Timestamp end_range);

// WAZNE! Argumenty oprocz @Param mozna przekazywac jako po prostu w sqlu '?1' ?Numer
// Zapytanie normalnie wyglada tak:
//  SELECT SUM(us.STORY_POINTS_AMOUNT) FROM SPRINT s
//    LEFT JOIN SPRINT_USER_STORY sus on s.ID = sus.SPRINT_ID
//    LEFT JOIN USERSTORY us on us.ID = sus.USER_STORY_ID
//  WHERE s.ID=1 AND us.STATUS LIKE 'DONE'
// Ale okazuje sie ze w JPQL (@Query) nie musimy laczyc niczego :)
// https://stackoverflow.com/questions/18592533/many-to-many-query-jpql
    @Query("SELECT SUM(sus.storyPointsAmount) FROM Sprint s JOIN s.userStorySet sus WHERE s.id=:sprintID AND sus.status LIKE 'DONE'")
    Integer getStoryPointsById(@Param("sprintID") Long id);


}

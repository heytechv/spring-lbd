package com.fisproject.springlbd.repository;


import com.fisproject.springlbd.entity.Sprint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {
    Optional<Sprint> findByName(String name);

//    @Query("SELECT s FROM Sprint s WHERE s.start_date BETWEEN :startRange AND :end_range")
    @Query("SELECT s FROM Sprint s WHERE s.start_date BETWEEN :startRange AND :endRange")
    List<Sprint> getByStartDateBetween(@Param("startRange") Timestamp start_range, @Param("endRange") Timestamp end_range);
}

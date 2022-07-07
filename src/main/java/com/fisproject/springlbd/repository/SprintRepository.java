package com.fisproject.springlbd.repository;


import com.fisproject.springlbd.entity.Sprint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {
    Optional<Sprint> findByName(String name);
//    @Query(value="SELECT * FROM Sprint") List<Sprint> getSprintList();
}

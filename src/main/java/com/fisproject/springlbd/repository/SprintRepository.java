package com.fisproject.springlbd.repository;


import com.fisproject.springlbd.entity.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {
    Optional<Sprint> findByName(String name);
}

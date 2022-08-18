package com.fisproject.springlbd.repository;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {
}

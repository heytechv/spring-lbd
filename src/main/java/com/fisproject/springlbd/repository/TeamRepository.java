package com.fisproject.springlbd.repository;

import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}

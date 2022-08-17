package com.fisproject.springlbd.repository;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}

package com.ul.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ul.api.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

package com.ul.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ul.api.exception.ResourceNotFoundException;
import com.ul.api.model.Project;
import com.ul.api.model.repository.ProjectRepository;

/**
 *
 * End points for projects.
 *
 */
@RestController
@RequestMapping("/v1/projects")
public class ProjectController implements HealthController {

    /**
     * Project repository.
     */
    @Autowired
    private ProjectRepository projectRepository;

    /**
     *
     * @return {@link List}
     */
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     *
     * @param project
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody final Project project) {
        projectRepository.save(project);
        return ResponseEntity.created(URI.create("/projects/")).body(project);
    }

    /**
     *
     * @param projectId
     * @param projectDetails
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable(value = "id") final long projectId,
            @RequestBody final Project projectDetails)
            throws ResourceNotFoundException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found :: " + projectId));

        project.setName(projectDetails.getName());
        project.setStatus(projectDetails.getStatus());
        final Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }
}

package com.cognizant.samservice.services;

import com.cognizant.samservice.model.ProjectEntity;

import java.util.List;

public interface ProjectService {
    ProjectEntity createProject(ProjectEntity project);
    ProjectEntity getProjectById(Long id);
    List<ProjectEntity> getAllProjects();
    ProjectEntity updateProject(Long id, ProjectEntity updatedProject);
    void deleteProject(Long id);
}

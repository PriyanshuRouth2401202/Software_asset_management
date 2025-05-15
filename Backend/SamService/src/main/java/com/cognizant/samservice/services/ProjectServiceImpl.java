package com.cognizant.samservice.services;

import com.cognizant.samservice.exception.ResourceNotFoundException;
import com.cognizant.samservice.model.ProjectEntity;
import com.cognizant.samservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectEntity createProject(ProjectEntity project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectEntity getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectEntity updateProject(Long id, ProjectEntity updatedProject) {
        ProjectEntity existingProject = getProjectById(id);

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.setEndDate(updatedProject.getEndDate());
        existingProject.setDepartment(updatedProject.getDepartment());
        // softwareAllocations and employeeProjects are managed separately usually

        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        ProjectEntity existingProject = getProjectById(id);
        projectRepository.delete(existingProject);
    }
}

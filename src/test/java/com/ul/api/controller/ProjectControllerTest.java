package com.ul.api.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ul.api.constant.StatusEnum;
import com.ul.api.model.Project;
import com.ul.api.model.repository.ProjectRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProjectRepository projectRepository;

    List<Project> projectList;

    @BeforeEach
    void contextLoads() {
        Project project1 = new Project("P1", StatusEnum.IN_PROGRESS, LocalDateTime.now(), false);
        Project project2 = new Project("P2", StatusEnum.IN_PROGRESS, LocalDateTime.now(), false);
        Project project3 = new Project("P", StatusEnum.FINISHED, LocalDateTime.now(), false);
        projectList = Arrays.asList(project1, project2, project3);
    }

    @Test
    public void shoud_pass_get_projects() throws Exception {
        String uri = "/v1/projects";
        when(projectRepository.findAll()).thenReturn(projectList);
        mvc.perform(get(uri)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(projectList.get(0).getName()))).andReturn();

    }

    @Test
    public void shoud_pass_create_project() throws Exception {
        String uri = "/v1/projects";

        Project project = projectList.get(0);
        doReturn(project).when(projectRepository).save(any());
        mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(project)))
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(project.getName()))).andReturn();
    }

    @Test
    public void shoud_pass_update_project() throws Exception {
        String uri = "/v1/projects/1";

        Project project = projectList.get(0);
        doReturn(Optional.of(project)).when(projectRepository).findById(any());
        project.setName("updatedname");
        doReturn(project).when(projectRepository).save(any());
        mvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(project)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("updatedname"))).andReturn();
    }

    @Test
    public void should_fail_update_project() throws Exception {
        String uri = "/v1/projects/100";
        String message = "Project not found :: 100";
        Project project = projectList.get(0);

        doReturn(Optional.empty()).when(projectRepository).findById(any());
        mvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(project)))
                .andExpect(status().isNotFound()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(message))).andReturn();
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

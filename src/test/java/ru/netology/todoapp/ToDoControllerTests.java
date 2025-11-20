package ru.netology.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.netology.todoapp.model.Task;
import ru.netology.todoapp.model.UpdateRequest;
import ru.netology.todoapp.service.ToDoService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc

public class ToDoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ToDoService service;

    @Test
    public void testAll() throws Exception {
        List<Task> tasks = Arrays.asList(
                new Task("First title", 10, true),
                new Task("Second title", 20, false)
        );

        when(service.all()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("First title"))
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].done").value(true))
                .andExpect(jsonPath("$[1].title").value("Second title"))
                .andExpect(jsonPath("$[1].id").value(20))
                .andExpect(jsonPath("$[1].done").value(false));
    }


    @Test
    public void testSave() throws Exception {
            Task task = new Task("New task", 123, false);

            when(service.save(any(Task.class))).thenReturn(task);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonTask = objectMapper.writeValueAsString(task);


            mockMvc.perform(post("/api/tasks")
                            .contentType("application/json")
                            .content(jsonTask))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("New task"))
                    .andExpect(jsonPath("$.id").value(123))
                    .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    public void testUpdateTitle() throws Exception {
        Integer id = 1;
        String newTitle = "Updated title";

        Task updatedTask = new Task(newTitle, id, false);
        when(service.updateTitle(eq(id), eq(newTitle))).thenReturn(updatedTask);

        UpdateRequest request = new UpdateRequest();
        request.setTitle(newTitle);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/tasks/" + id)
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(newTitle));

        verify(service).updateTitle(eq(id), eq(newTitle));
    }

    @Test
    public void testUpdateDone() throws Exception {
        Integer id = 1;
        Boolean newDone = true;

        Task updatedTask = new Task("Some task", id, newDone);
        when(service.updateDone(eq(id), eq(newDone))).thenReturn(updatedTask);

        UpdateRequest request = new UpdateRequest();
        request.setDone(newDone);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/tasks/" + id)
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done").value(newDone));

        verify(service).updateDone(eq(id), eq(newDone));
    }

}

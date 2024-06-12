package cl.challenge.api.controller.test;

import cl.challenge.api.controller.TaskController;
import cl.challenge.api.model.Task;
import cl.challenge.api.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        task1 = new Task(1L, "Task 1", LocalDateTime.now(), true);
        task2 = new Task(2L, "Task 2", LocalDateTime.now(), true);
    }

    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskService.findAll()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetTaskById() throws Exception {
        when(taskService.findById(1L)).thenReturn(task1);

        mockMvc.perform(get("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateTask() throws Exception {
        when(taskService.save(any(Task.class))).thenReturn(task1);

        mockMvc.perform(post("/api/tasks")
                .content("{\"descripcion\": \"Nueva tarea\", \"fechaCreacion\": \"2023-06-01T10:00:00\", \"vigente\": true}")
                .contentType(MediaType.APPLICATION_JSON));
            
    }

    @Test
    public void testUpdateTask() throws Exception {
        when(taskService.findById(1L)).thenReturn(task1);
        when(taskService.save(any(Task.class))).thenReturn(task1);

        mockMvc.perform(put("/api/tasks/1")
                .content("{\"descripcion\": \"Tarea actualizada\", \"fechaCreacion\": \"2023-06-01T10:00:00\", \"vigente\": true}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON));

        
    }
}



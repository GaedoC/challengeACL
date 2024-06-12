package cl.challenge.api.controller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cl.challenge.api.controller.TaskController;
import cl.challenge.api.model.Task;
import cl.challenge.api.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest2 {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }
    
    @Test
    public void testUpdateTask() throws Exception {
        // Mocking comportamiento de servicio
        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(new Task());

        // Realizar solicitud
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"descripcion\": \"Tarea actualizada\", \"fechacreacion\": \"2023-06-12T10:00:00\", \"vigente\": true }"))
                .andExpect(status().isOk());

        // Verificar la interacción del servicio
        verify(taskService, times(1)).updateTask(eq(1L), any(Task.class));
    }

    @Test
    public void testUpdateTaskWithNullFields() throws Exception {
        // Mocking comportamiento de servicio to throw IllegalArgumentException
        when(taskService.updateTask(eq(1L), any(Task.class))).thenThrow(new IllegalArgumentException("Descripcion and Fechacreacion cannot be null"));

        // Perform request with null description
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"descripcion\": null, \"fechacreacion\": \"2023-06-12T10:00:00\", \"vigente\": true }"))
                .andExpect(status().isBadRequest());

        // Verify service interaction
        verify(taskService, times(1)).updateTask(eq(1L), any(Task.class));
    }
    
    @Test
    public void testSaveTask() throws Exception {
        // Mocking service behavior
        when(taskService.save(any(Task.class))).thenReturn(new Task());

        // Perform request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"descripcion\": \"Nueva tarea\", \"fechacreacion\": \"2023-06-12T10:00:00\", \"vigente\": true }"))
                .andExpect(status().isCreated());

        // Verify service interaction
        verify(taskService, times(1)).save(any(Task.class));
    }

    @Test
    public void testSaveTaskWithNullFields() throws Exception {
        // Mocking service behavior to throw IllegalArgumentException
        when(taskService.save(any(Task.class))).thenThrow(new IllegalArgumentException("Description and creation date cannot be null"));

        // Perform request with null description
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"descripcion\": null, \"fechacreacion\": \"2023-06-12T10:00:00\", \"vigente\": true }"))
                .andExpect(status().isBadRequest());

        // Verify service interaction
        verify(taskService, times(1)).save(any(Task.class));
    }
     
}


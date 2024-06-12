package cl.challenge.api.service.test;


import cl.challenge.api.model.Task;
import cl.challenge.api.repository.TaskRepository;
import cl.challenge.api.service.TaskServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	
	    @Mock
	    private TaskRepository taskRepository;

	    @InjectMocks
	    private TaskServiceImpl taskService;

	    private Task mockTask;
	    
	    @BeforeEach
	    public void setUp() {
	        mockTask = new Task();
	        mockTask.setId(1L);
	        mockTask.setDescripcion("Tarea de prueba");
	        mockTask.setFechaCreacion(LocalDateTime.now());
	        mockTask.setVigente(true);
	    }
	    
	    @Test
	    public void testUpdateTask() {
	        // Mocking repository behavior
	        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(mockTask));
	        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

	        // Perform update
	        Task updatedTask = taskService.updateTask(1L, mockTask);

	        // Verify repository interactions
	        verify(taskRepository, times(1)).findById(eq(1L));
	        verify(taskRepository, times(1)).save(any(Task.class));

	        // Assertions
	        assert updatedTask != null;
	        assert updatedTask.getId().equals(1L);
	        assert updatedTask.getDescripcion().equals("Tarea de prueba");
	        // Add more assertions as needed
	    }

	    @Test
	    public void testUpdateTaskWithNullFields() {
	        // Mocking repository behavior
	        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(mockTask));

	        // Set description to null
	        mockTask.setDescripcion(null);

	        // Verify that IllegalArgumentException is thrown
	        assertThrows(IllegalArgumentException.class, () -> {
	            taskService.updateTask(1L, mockTask);
	        });

	        // Verify that save is never called
	        verify(taskRepository, never()).save(any(Task.class));
	    }
	    
	    @Test
	    public void testSaveTask() {
	        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

	        Task savedTask = taskService.save(mockTask);

	        verify(taskRepository, times(1)).save(mockTask);
	    }

	    @Test
	    public void testSaveTaskWithNullFields() {
	        mockTask.setDescripcion(null);

	        assertThrows(IllegalArgumentException.class, () -> {
	            taskService.save(mockTask);
	        });

	        verify(taskRepository, never()).save(mockTask);
	    }

   
}

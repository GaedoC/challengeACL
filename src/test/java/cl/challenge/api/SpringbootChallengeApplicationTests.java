package cl.challenge.api;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpringbootChallengeApplicationTests {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        task1 = new Task(1L, "Task 1", LocalDateTime.now(), true);
        task2 = new Task(2L, "Task 2", LocalDateTime.now(), true);
    }

    @Test
    public void testFindAll() {
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Task result = taskService.findById(1L);
        assertNotNull(result);
        assertEquals("Task 1", result.getDescripcion());
    }

    @Test
    public void testSave() {
        when(taskRepository.save(any(Task.class))).thenReturn(task1);

        Task result = taskService.save(task1);
        assertNotNull(result);
        assertEquals("Task 1", result.getDescripcion());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}


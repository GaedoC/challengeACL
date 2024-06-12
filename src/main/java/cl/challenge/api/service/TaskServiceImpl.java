package cl.challenge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import cl.challenge.api.error.TaskNotFoundException;
import cl.challenge.api.model.Task;
import cl.challenge.api.repository.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task save(Task task) {
        if (task.getDescripcion()==null|| task.getFechaCreacion() == null || task.getDescripcion().isBlank() ) {
            throw new IllegalArgumentException("Descripción o fecha de creación no pueden ser nulos ni vacios.");
        }       
        return taskRepository.save(task);
    }
    
    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
    
    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada con id: " + id));

        if (task.getDescripcion()==null || task.getFechaCreacion()==null || task.getDescripcion().isBlank() )   {
            throw new IllegalArgumentException("Descripción o fecha de creación no pueden ser nulos ni vacios");
        }

        existingTask.setDescripcion(task.getDescripcion());
        existingTask.setFechaCreacion(task.getFechaCreacion());
        existingTask.setVigente(task.isVigente());

        return taskRepository.save(existingTask);
    }
}

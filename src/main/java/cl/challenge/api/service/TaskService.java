package cl.challenge.api.service;

import cl.challenge.api.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
    Task updateTask(Long id, Task task);
}

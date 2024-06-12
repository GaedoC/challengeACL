package cl.challenge.api.config;

import cl.challenge.api.model.Task;
import cl.challenge.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        // Cargar datos de ejemplo
        Task task1 = new Task(null, "Tarea 1", LocalDateTime.now(), true);
        Task task2 = new Task(null, "Tarea 2", LocalDateTime.now(), false);
        Task task3 = new Task(null, "Tarea 3", LocalDateTime.now(), true);
        Task task4 = new Task(null, "Tarea 4", LocalDateTime.now(), false);
        Task task5 = new Task(null, "Tarea 5", LocalDateTime.now(), true);
        Task task6 = new Task(null, "Tarea 6", LocalDateTime.now(), false);
        Task task7 = new Task(null, "Tarea 7", LocalDateTime.now(), true);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);
        taskRepository.save(task7);
    }
}

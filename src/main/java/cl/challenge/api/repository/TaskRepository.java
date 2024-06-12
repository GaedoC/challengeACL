package cl.challenge.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import cl.challenge.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
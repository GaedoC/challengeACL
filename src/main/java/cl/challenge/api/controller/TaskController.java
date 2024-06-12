package cl.challenge.api.controller;

import cl.challenge.api.error.TaskNotFoundException;
import cl.challenge.api.model.Task;
import cl.challenge.api.service.TaskService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	@ApiOperation(value = "Obtener todas las tareas", notes = "Recuperar todas las tareas de la base de datos.")
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obtener tarea por ID", notes = "Recuperar una tarea por su ID único.")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Task task = taskService.findById(id);
		return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Eliminar una tarea", notes = "Eliminar una tarea de la base de datos.")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		Task task = taskService.findById(id);
		if (task != null) {
			taskService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody Task task,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("Cuerpo de solicitud no válido.");
		}

		try {
			Task updatedTask = taskService.updateTask(id, task);
			return ResponseEntity.ok(updatedTask);
		} catch (TaskNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la tarea");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveTask(@Valid @RequestBody Task task, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("Cuerpo de solicitud no válido.");
		}

		try {
			Task savedTask = taskService.save(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
		} catch (TaskNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar tarea");
		}
	}
}
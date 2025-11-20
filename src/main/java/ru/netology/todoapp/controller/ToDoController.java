package ru.netology.todoapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.todoapp.model.Task;
import ru.netology.todoapp.model.UpdateRequest;
import ru.netology.todoapp.service.ToDoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")

public class ToDoController {
    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> all() {
        return service.all();
    }

    @PostMapping
    public Task save(@RequestBody Task task) {
        return service.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Integer id,
                       @RequestBody UpdateRequest request) {
        if (request.getDone() == null) {
            return service.updateTitle(id, request.getTitle());
        }
        if (request.getTitle() == null) {
           return service.updateDone(id,request.getDone());
        }
        throw new IllegalArgumentException("Неверный тип данных");
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable Integer id) {
        service.delete(id);
    }
}

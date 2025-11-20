package ru.netology.todoapp.service;

import org.springframework.stereotype.Service;
import ru.netology.todoapp.model.Task;
import ru.netology.todoapp.repository.ToDoRepository;

import java.util.List;
import java.util.Optional;

@Service

public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public List<Task> all() {
        return repository.all();
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public Task updateDone(Integer id, Boolean done) {
        return repository.updateDone(id, done);
    }

    public Task updateTitle(Integer id, String title) {
        return repository.updateTitle(id, title);
    }

    public void delete(Integer id) {
        repository.deleteTaskById(id);
    }

}

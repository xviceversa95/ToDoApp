package ru.netology.todoapp.repository;

import org.springframework.stereotype.Repository;
import ru.netology.todoapp.model.Task;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository

public class ToDoRepository {
    public ConcurrentHashMap<Integer, Task> repository = new ConcurrentHashMap<>();

    public List<Task> all(){
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : repository.entrySet()) {
            taskList.add(entry.getValue());
        }
        return taskList;
    }

    //если есть - меняем, если нет - добавляем
    public Task save(Task task){
        if (repository.containsKey(task.getId())) {
            task.setCreatedAt(LocalDateTime.now());
            repository.replace(task.getId(), task);
        } else {
            task.setCreatedAt(LocalDateTime.now());
            repository.put(task.getId(), task);
        }
        return task;
    }

    public Task updateTitle(Integer id, String title) {
        if (repository.containsKey(id)) {
            repository.get(id).setTitle(title);
        } else {
            repository.put(id, new Task(title, id));
        }
        return repository.get(id);
    }

    public Task updateDone(Integer id, Boolean done) {
        if (repository.containsKey(id)) {
            repository.get(id).setDone(done);
        } else {
            throw new RuntimeException("Нет такого элемента");
        }
        return repository.get(id);
    }


    public void deleteTaskById(Integer id){
        repository.remove(id);
    }
}

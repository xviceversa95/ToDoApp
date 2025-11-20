package ru.netology.todoapp.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Task {
    public String title;
    public Integer id;
    public boolean done;
    public LocalDateTime createdAt;

    public Task() {}

    public Task(String title, Integer id, boolean done) {
        this.title = title;
        this.id = id;
        this.done = done;
    }

    public Task(String title, Integer id) {
        this.title = title;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}



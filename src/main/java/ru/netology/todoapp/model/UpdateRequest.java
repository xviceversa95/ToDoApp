package ru.netology.todoapp.model;

public class UpdateRequest {
    public String title;
    public Boolean done;

    public UpdateRequest(String title, Boolean done) {
        this.title = title;
        this.done = done;
    }

    public UpdateRequest() {}

    public String getTitle() {
        return title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


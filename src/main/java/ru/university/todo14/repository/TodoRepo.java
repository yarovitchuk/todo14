package ru.university.todo14.repository;

import ru.university.todo14.model.Todo;

import java.util.*;

public class TodoRepo {

    private final Map<UUID, Todo> data;

    public TodoRepo() {
        this.data = new HashMap<>();
    }

    public TodoRepo(Map<UUID, Todo> data) {
        this.data = data;
    }

    public Todo create(Todo newTodo) {
        data.put(newTodo.getId(), newTodo);
        return newTodo;
    }

    public Todo update(Todo updatedTodo) {
        data.put(updatedTodo.getId(), updatedTodo);
        return updatedTodo;
    }

    public boolean delete(UUID id) {
        return data.remove(id) != null;
    }

    public Optional<Todo> getById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<Todo> getAll() {
        return data.values().stream().toList();
    }
}

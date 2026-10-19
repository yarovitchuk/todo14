package ru.university.todo14.repository;

import ru.university.todo14.model.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoRepository {

    List<Todo> getAll();

    Todo create(Todo todo);

    Todo update(Todo todo);

    boolean delete(UUID id);

    Optional<Todo> getById(UUID id);
}

package ru.university.todo14.service;

import ru.university.todo14.exception.TodoNotFoundException;
import ru.university.todo14.exception.TodoTitleException;
import ru.university.todo14.model.Todo;
import ru.university.todo14.repository.TodoRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public Todo create(Todo newTodo) {
        if (newTodo.getTitle().isBlank()) {
            throw new TodoTitleException();
        }

        return todoRepo.create(newTodo);
    }

    public Todo update(Todo updatedTodo) {
        if (updatedTodo.getTitle().isBlank()) {
            throw new TodoTitleException();
        }

        return todoRepo.getById(updatedTodo.getId())
                .map(_ -> todoRepo.update(updatedTodo))
                .orElseThrow(TodoNotFoundException::new);
    }

    public void deleteById(UUID id) {
        todoRepo.getById(id)
                .map(_ -> todoRepo.delete(id))
                .orElseThrow(TodoNotFoundException::new);
    }

    public Optional<Todo> getById(UUID id) {
        return todoRepo.getById(id);
    }

    public List<Todo> getAll() {
        return todoRepo.getAll();
    }
}

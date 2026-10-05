package ru.university.todo14.service;

import ru.university.todo14.exception.TodoNotFoundException;
import ru.university.todo14.exception.TodoTitleException;
import ru.university.todo14.model.Todo;
import ru.university.todo14.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo newTodo) {
        if (newTodo.getTitle().isBlank()) {
            throw new TodoTitleException();
        }

        return todoRepository.create(newTodo);
    }

    public Todo update(Todo updatedTodo) {
        if (updatedTodo.getTitle().isBlank()) {
            throw new TodoTitleException();
        }

        return todoRepository.getById(updatedTodo.getId())
                .map(_ -> todoRepository.update(updatedTodo))
                .orElseThrow(() -> new TodoNotFoundException(updatedTodo.getId()));
    }

    public void deleteById(UUID id) {
        todoRepository.getById(id)
                .map(_ -> todoRepository.delete(id))
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Optional<Todo> getById(UUID id) {
        return todoRepository.getById(id);
    }

    public List<Todo> getAll() {
        return todoRepository.getAll();
    }
}

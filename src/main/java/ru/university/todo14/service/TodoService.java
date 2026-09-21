package ru.university.todo14.service;

import ru.university.todo14.exception.TodoNotFoundException;
import ru.university.todo14.exception.TodoTitleException;
import ru.university.todo14.model.Todo;
import ru.university.todo14.repository.TodoRepo;

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
                .map(_ -> {
                    todoRepo.update(updatedTodo);
                    return updatedTodo;
                })
                .orElseThrow(TodoNotFoundException::new);
    }
}

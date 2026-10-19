package ru.university.todo14;


import ru.university.todo14.controller.CmdController;
import ru.university.todo14.model.Todo;
import ru.university.todo14.repository.TodoRepositoryInMemoryImpl;
import ru.university.todo14.service.TodoService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 * 1. Написать консольное приложение
 * 2. CRUD, create, read, update, delete
 * 3. Хранение данных в Map или List
 * 4. todo list для демонстрации
 */

public class Main {
    public static void main(String[] args) {
        Map<UUID, Todo> todoMap = new HashMap<>();
        TodoRepositoryInMemoryImpl todoRepositoryInMemoryImpl = new TodoRepositoryInMemoryImpl(todoMap);
        TodoService todoService = new TodoService(todoRepositoryInMemoryImpl);
        Scanner scanner = new Scanner(System.in);
        CmdController cmdController = new CmdController(todoService, scanner);
        cmdController.run();
    }
}
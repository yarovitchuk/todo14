package ru.university.todo14;


import ru.university.todo14.controller.CmdController;
import ru.university.todo14.repository.TodoRepositoryInMemoryImpl;
import ru.university.todo14.service.TodoService;

import java.util.Scanner;

/**
 * 1. Написать консольное приложение
 * 2. CRUD, create, read, update, delete
 * 3. Хранение данных в Map или List
 * 4. todo list для демонстрации
 */

public class Main {
    public static void main(String[] args) {
        TodoRepositoryInMemoryImpl todoRepositoryInMemoryImpl = new TodoRepositoryInMemoryImpl();
        TodoService todoService = new TodoService(todoRepositoryInMemoryImpl);
        Scanner scanner = new Scanner(System.in);
        CmdController cmdController = new CmdController(todoService, scanner);
        cmdController.run();
    }
}
package ru.university.todo14.controller;

import ru.university.todo14.model.Todo;
import ru.university.todo14.service.TodoService;

import java.util.Scanner;
import java.util.UUID;

public class CmdController {

    private final TodoService todoService;
    private final Scanner scanner;

    public CmdController(TodoService todoService, Scanner scanner) {
        this.todoService = todoService;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("\n=== TO-DO МЕНЮ ===");
            System.out.println("1. Показать все задачи");
            System.out.println("2. Найти задачу по ID");
            System.out.println("3. Создать задачу");
            System.out.println("4. Обновить задачу");
            System.out.println("5. Удалить задачу");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        getAll();
                        break;

                    case "2":
                        getById();
                        break;

                    case "3":
                        createTodo();
                        break;

                    case "4":
                        updateTodo();
                        break;

                    case "5":
                        break;

                    case "0":
                        System.out.println("Выход из программы. Пока!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                }
            } catch (Exception e) {
                System.out.println("Возникла непредвиденная ошибка");
            }
        }
    }

    private void getAll() {
        todoService.getAll()
                .stream()
                .map(this::formatTodo)
                .forEach(System.out::println);
    }

    private void getById() {
        System.out.print("Введите id задачи: ");
        String rawId = scanner.nextLine();
        try {
            UUID id = UUID.fromString(rawId);
            todoService.getById(id)
                    .map(this::formatTodo)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Задачи с таким идентификатором не найдено")
                    );
        } catch (Exception e) {
            System.out.println("Неверно задан идентификатор");
        }
    }

    private void createTodo() {
        System.out.print("Введите заголовок задачи: ");
        String title = scanner.nextLine();
        Todo todo = new Todo(UUID.randomUUID(), title);
        Todo created = todoService.create(todo);
        System.out.println("Создана задача: " + formatTodo(created));
    }

    private String formatTodo(Todo todo) {
        return String.format("id = %s, title = %s", todo.getId(), todo.getTitle());
    }
}
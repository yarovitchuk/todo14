package ru.university.todo14.controller;

import ru.university.todo14.model.Todo;
import ru.university.todo14.service.TodoService;

import java.util.List;
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
                        deleteTodo();
                        break;

                    case "0":
                        System.out.println("Выход из программы. Пока!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                }
            } catch (Exception e) {
                System.out.println("Возникла непредвиденная ошибка: " + e.getMessage());
            }
        }
    }

    private void getAll() {
        List<Todo> all = todoService.getAll();
        if (all.isEmpty()) {
            System.out.println("Нет созданных задач");
            return;
        }

        all.stream()
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

    private void updateTodo() {
        UUID uuid;
        while (true) {
            System.out.print("Введите идентификатор задачи: ");
            String id = scanner.nextLine();
            try {
                uuid = UUID.fromString(id);
                break;
            } catch (Exception e) {
                System.out.println("Неверно задан идентификатор. Введите заново");
            }
        }
        todoService.getById(uuid)
                .ifPresentOrElse(
                        todo -> {
                            System.out.print("Введите новый заголовок задачи: ");
                            String newTitle = scanner.nextLine();
                            todo.setTitle(newTitle);
                            todoService.update(todo);
                        },
                        () -> System.out.println("Нет задачи с таким идентификатором")
                );
    }

    private void deleteTodo() {
        UUID uuid;
        while (true) {
            System.out.print("Введите идентификатор задачи: ");
            String id = scanner.nextLine();
            try {
                uuid = UUID.fromString(id);
                break;
            } catch (Exception e) {
                System.out.println("Неверно задан идентификатор. Введите заново");
            }
        }
        todoService.deleteById(uuid);
    }


    private String formatTodo(Todo todo) {
        return String.format("id = %s, title = %s", todo.getId(), todo.getTitle());
    }
}
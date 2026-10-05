package ru.university.todo14.exception;

public class TodoTitleException extends BusinessException {
    public TodoTitleException() {
        super("Title is blank");
    }
}

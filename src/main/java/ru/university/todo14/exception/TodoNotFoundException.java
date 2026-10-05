package ru.university.todo14.exception;

import java.util.UUID;

public class TodoNotFoundException extends BusinessException {
    public TodoNotFoundException(UUID uuid) {
        super("Todo not found with id = " + uuid);
    }
}
